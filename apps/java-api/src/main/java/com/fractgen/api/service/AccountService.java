package com.fractgen.api.service;

import com.fractgen.api.dto.AccountDTO;
import com.fractgen.api.dto.AccountName;
import com.fractgen.api.exception.ResourceIncompatibleException;
import com.fractgen.api.exception.ResourceNotFoundException;
import com.fractgen.api.model.Account;
import com.fractgen.api.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
  @Autowired
  AccountRepo accountRepo;

  public List<Account> getAllAccounts () {
    return accountRepo.findAll();
  }

  public Optional<Account> getAccountById (long id){
    return accountRepo.findById(id);
  }

  public long login (AccountDTO accountDTO) throws ResourceNotFoundException, ResourceIncompatibleException {
    Account account = accountRepo.findByEmail(accountDTO.getEmail()).orElseThrow(ResourceNotFoundException::new);
    if (!account.getPassword().equals(accountDTO.getPassword())) {
      throw new ResourceIncompatibleException();
    }
    return account.getId();
  }

  public boolean existsByEmail (String email){
    return accountRepo.existsByEmail(email);
  }

  public Account addAccount (AccountName account) {
    Account accountToSave = new Account();
    accountToSave.setEmail(account.getAccount().getEmail());
    accountToSave.setPassword(account.getAccount().getPassword());
    accountToSave.setProfile(null);
    return accountRepo.save(accountToSave);
  }

  public Account updateAccount (long id, Account account) {
    Account accountToUpdate = accountRepo.findById(id).get();
    if(account.getEmail() != null) {
      accountToUpdate.setEmail(account.getEmail());
    }
    if(account.getPassword() != null) {
      accountToUpdate.setPassword(account.getPassword());
    }
    if(account.getProfile() != null) {
      accountToUpdate.setProfile(account.getProfile());
    }

    return accountRepo.save(accountToUpdate);
  }

  public void deleteAccount (long id) {
    accountRepo.deleteById(id);
  }

  public boolean accountExists (long id) {
    return accountRepo.existsById(id);
  }
}
