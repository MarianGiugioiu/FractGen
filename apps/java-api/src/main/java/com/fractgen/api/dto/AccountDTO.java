package com.fractgen.api.dto;

import java.util.Objects;

public class AccountDTO {
  private String email;

  private String password;

  public AccountDTO(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public AccountDTO() {
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AccountDTO)) return false;
    AccountDTO that = (AccountDTO) o;
    return Objects.equals(email, that.email) &&
      Objects.equals(password, that.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, password);
  }
}
