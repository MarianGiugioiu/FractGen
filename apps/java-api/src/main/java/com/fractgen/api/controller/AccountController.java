package com.fractgen.api.controller;

import com.fractgen.api.dto.AccountDTO;
import com.fractgen.api.dto.AccountName;
import com.fractgen.api.exception.ResourceIncompatibleException;
import com.fractgen.api.exception.ResourceNotFoundException;
import com.fractgen.api.model.Account;
import com.fractgen.api.model.Profile;
import com.fractgen.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
  @Autowired
  AccountService accountService;

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

  @PostMapping(value = "/login")
  public ResponseEntity<Long> login (@RequestBody AccountDTO accountDTO) {
    long id;
    try {
      id = accountService.login(accountDTO);
    } catch (ResourceNotFoundException e) {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "No Account found with this email", new ResourceNotFoundException()
      );
    } catch (ResourceIncompatibleException e) {
      throw new ResponseStatusException(
        HttpStatus.FORBIDDEN, "Password is incorrect", new ResourceIncompatibleException()
      );
    }
    return new ResponseEntity<>(id, HttpStatus.OK);
  }

  @GetMapping(value = "/exists/email/{email}")
  public ResponseEntity<Boolean> existsByName (@PathVariable("email") String email){
    boolean exists = accountService.existsByEmail(email);
    return new ResponseEntity<>(exists, HttpStatus.OK);
  }

  @PostMapping(value = {"", "/"})
  public ResponseEntity<Account> addAccount (@RequestBody AccountName account) {
    Account savedAccount = accountService.addAccount(account);
    account.setAccount(savedAccount);
    ResponseEntity<Profile> profile = profileController.addProfileWithAccount(account);
    savedAccount.setProfile(profile.getBody());
    savedAccount = accountService.updateAccount(savedAccount.getId(),savedAccount);
    return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
  }

  @PutMapping(value = ("/{id}"))
  public ResponseEntity<Account> updateAccount (@PathVariable("id") long id,
                                                @RequestBody Account account) {
    if (accountService.accountExists(id)) {
      Account updatedAccount = accountService.updateAccount(id, account);
      return new ResponseEntity<>(updatedAccount, HttpStatus.ACCEPTED);
    } else {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Cannot update non-existing Account", new ResourceNotFoundException()
      );
    }
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
