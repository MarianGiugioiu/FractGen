package com.fractgen.api.dto;

import java.util.Objects;

public class ResetPassword {
  private String password;

  public ResetPassword(String password) {
    this.password = password;
  }

  public ResetPassword() {
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
    if (!(o instanceof ResetPassword)) return false;
    ResetPassword that = (ResetPassword) o;
    return Objects.equals(password, that.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(password);
  }
}
