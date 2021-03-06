package com.fractgen.api.dto;

import java.util.Objects;

public class NewPassword {
  private long id;
  private String password;
  private String newPassword;

  public NewPassword(String password, String newPassword) {
    this.password = password;
    this.newPassword = newPassword;
  }

  public NewPassword() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof NewPassword)) return false;
    NewPassword that = (NewPassword) o;
    return id == that.id &&
      Objects.equals(password, that.password) &&
      Objects.equals(newPassword, that.newPassword);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, password, newPassword);
  }
}
