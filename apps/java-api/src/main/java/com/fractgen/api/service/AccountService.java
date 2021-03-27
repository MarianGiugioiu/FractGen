package com.fractgen.api.service;

import com.fractgen.api.model.Account;
import com.fractgen.api.model.Fractal;
import com.fractgen.api.repo.AccountRepo;
import com.fractgen.api.repo.FractalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
  @Autowired
  AccountRepo accountRepo;
  @Autowired
  FractalRepo fractalRepo;

  public List<Account> getAllAccounts () {
    return accountRepo.findAll();
  }

  public Optional<Account> getAccountById (long id){
    return accountRepo.findById(id);
  }

  public List<Fractal> getAllFractals (long id) {
    return fractalRepo.findByAccountId(id);
  }

  public Account addAccount (Account account) {
    Account accountToSave = new Account();

    return accountRepo.save(accountToSave);
  }

  public Account updateAccount (long id, Account account) {
    Account accountToUpdate = accountRepo.findById(id).get();

    return accountRepo.save(accountToUpdate);
  }

  public void deleteAccount (long id) {
    accountRepo.deleteById(id);
  }

  public boolean accountExists (long id) {
    return accountRepo.existsById(id);
  }
}
