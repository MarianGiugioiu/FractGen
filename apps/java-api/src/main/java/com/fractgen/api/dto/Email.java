package com.fractgen.api.dto;

import java.util.Objects;

public class Email {
  private String email;

  public Email(String email) {
    this.email = email;
  }

  public Email() {
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Email)) return false;
    Email email1 = (Email) o;
    return Objects.equals(email, email1.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email);
  }
}
