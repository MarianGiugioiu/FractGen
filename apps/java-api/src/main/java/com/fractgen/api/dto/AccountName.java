package com.fractgen.api.dto;

import com.fractgen.api.model.Account;

import java.util.Objects;

public class AccountName {
  private Account account;
  private String name;

  public AccountName() {
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AccountName)) return false;
    AccountName that = (AccountName) o;
    return Objects.equals(account, that.account) &&
      Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(account, name);
  }
}
