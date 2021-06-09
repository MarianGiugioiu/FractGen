package com.fractgen.api.controller;

import com.fractgen.api.dto.*;
import com.fractgen.api.exception.EmailNotVerifiedException;
import com.fractgen.api.exception.ResourceIncompatibleException;
import com.fractgen.api.exception.ResourceNotFoundException;
import com.fractgen.api.model.Account;
import com.fractgen.api.model.Profile;
import com.fractgen.api.service.AccountService;
import com.fractgen.api.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
  @Autowired
  AccountService accountService;
  @Autowired
  ProfileService profileService;

  @Autowired
  ProfileController profileController;

  @GetMapping(value = {"", "/"})
  public ResponseEntity<List<Account>> getAllAccounts () {
    List<Account> accountList = accountService.getAllAccounts();
    return new ResponseEntity<>(accountList, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Account> getAccountById (@PathVariable("id") long id){
    Account account = accountService.getAccountById(id)
      .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "No Account found with this ID", new ResourceNotFoundException()
      ));
    return new ResponseEntity<>(account, HttpStatus.OK);
  }

  @GetMapping(value = "/exists/email/{email}")
  public ResponseEntity<Boolean> existsByName (@PathVariable("email") String email){
    boolean exists = accountService.existsByEmail(email);
    return new ResponseEntity<>(exists, HttpStatus.OK);
  }

  @PostMapping(value = "/login")
  public ResponseEntity<Long> login (@RequestBody AccountDTO accountDTO) {
    long id;
    try {
      id = accountService.login(accountDTO);
    } catch (ResourceNotFoundException e) {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "No Account found with this email", new ResourceNotFoundException()
      );
    } catch (EmailNotVerifiedException e) {
      throw new ResponseStatusException(
        HttpStatus.UNAUTHORIZED, "This account is not verified", new EmailNotVerifiedException()
      );
    } catch (ResourceIncompatibleException e) {
      throw new ResponseStatusException(
        HttpStatus.FORBIDDEN, "Password is incorrect", new ResourceIncompatibleException()
      );
    }
    return new ResponseEntity<>(id, HttpStatus.OK);
  }

  @PostMapping("/process_register")
  public String processRegister(@RequestBody AccountName accountName, HttpServletRequest request)
    throws UnsupportedEncodingException, MessagingException {
    if (accountService.existsByEmail(accountName.getAccount().getEmail())) {
      return "email_exists";
    }
    if (profileService.existsByName(accountName.getName())) {
      return "name_exists";
    }
    Account savedAccount = accountService.register(accountName, getSiteURL(request));
    accountName.setAccount(savedAccount);
    ResponseEntity<Profile> profile = profileController.addProfileWithAccount(accountName);
    savedAccount.setProfile(profile.getBody());
    accountService.updateAccount(savedAccount.getId(), savedAccount);
    return "register_success";
  }

  @PostMapping("/process_reset_password")
  public String processResetPassword(@RequestBody Email email, HttpServletRequest request)
    throws UnsupportedEncodingException, MessagingException {
    String result = "Can't send mail";
    try {
      result = accountService.ProcessPasswordReset(email, getSiteURL(request));
    } catch (ResourceNotFoundException e) {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "No Account found with this email", new ResourceNotFoundException()
      );
    }
    return result;
  }

  private String getSiteURL(HttpServletRequest request) {
    String siteURL = "http://localhost:4200";
    return siteURL.replace(request.getServletPath(), "");
  }

  @GetMapping("/verify/{code}")
  public String verifyUser(@PathVariable("code") String code) {
    if (accountService.verify(code)) {
      return "verify_success";
    } else {
      return "verify_fail";
    }
  }

  @GetMapping("/verify_reset/{code}")
  public String verifyResetPassword(@PathVariable("code") String code) {
    if (accountService.verifyReset(code)) {
      return "verify_success";
    } else {
      return "verify_fail";
    }
  }

  @PutMapping(value = ("/reset_password/{code}"))
  public String ResetPassword (@PathVariable("code") String code, @RequestBody ResetPassword password) {
    String result;
    try {
      result = accountService.resetPassword(code, password.getPassword());
    } catch (ResourceNotFoundException e) {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Cannot find account for the given code", new ResourceNotFoundException()
      );
    }
    return result;
  }

  @PutMapping(value = ("/change_password"))
  public String ChangePassword (@RequestBody NewPassword newPassword) {
    String result = "Error";
    try{
      result = accountService.changePassword(newPassword);
    } catch (ResourceNotFoundException e){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Cannot update non-existing Account", new ResourceNotFoundException()
      );
    } catch (ResourceIncompatibleException e) {
      throw new ResponseStatusException(
        HttpStatus.FORBIDDEN, "Password is incorrect", new ResourceIncompatibleException()
      );
    }
    return result;
  }

  @DeleteMapping(value = ("/{id}"))
  public ResponseEntity<HttpStatus> deleteAccount (@PathVariable("id") long id) {
    if (accountService.accountExists(id)) {
      accountService.deleteAccount(id);
      return new ResponseEntity<>(HttpStatus.ACCEPTED);
    } else {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Cannot delete non-existing Account", new ResourceNotFoundException()
      );
    }
  }

}
