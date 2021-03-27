package com.fractgen.api.controller;

import com.fractgen.api.exception.ResourceNotFoundException;
import com.fractgen.api.model.Account;
import com.fractgen.api.model.Fractal;
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

  @GetMapping(value = "/{id}/fractals")
  public ResponseEntity<List<Fractal>> getFractalsByAccountId (@PathVariable("id") long id){
    List<Fractal> fractalListList = accountService.getAllFractals(id);
    return new ResponseEntity<>( fractalListList, HttpStatus.OK);
  }

  @PostMapping(value = {"", "/"})
  public ResponseEntity<Account> addAccount (@RequestBody Account account) {
    Account savedAccount = accountService.addAccount(account);
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
