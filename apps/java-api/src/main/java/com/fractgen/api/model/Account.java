package com.fractgen.api.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fractgen.api.serializer.FractalSerializer;
import com.fractgen.api.serializer.ProfileSerializer;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "account")
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "account_id")
  private long id;

  @Column(name = "account_email")
  private String email;

  @Column(name = "account_password")
  private String password;

  @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval=true)
  @JsonSerialize(using = ProfileSerializer.class)
  private Profile profile;

  public Account() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  public Profile getProfile() {
    return profile;
  }

  public void setProfile(Profile profile) {
    this.profile = profile;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Account)) return false;
    Account account = (Account) o;
    return id == account.id &&
      Objects.equals(email, account.email) &&
      Objects.equals(password, account.password) &&
      Objects.equals(profile, account.profile);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, password, profile);
  }
}
