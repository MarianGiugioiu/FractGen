package com.fractgen.api.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fractgen.api.serializer.FractalSerializer;
import com.fractgen.api.serializer.ProfileSerializer;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "account")
public class Account {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
    name = "UUID",
    strategy = "org.hibernate.id.UUIDGenerator",
    parameters = {
      @org.hibernate.annotations.Parameter(
        name = "uuid_gen_strategy_class",
        value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
      )
    }
  )
  @Type(type="uuid-char")
  @Column(name = "account_id", updatable = false, nullable = false)
  private UUID id;

  @Column(name = "verification_code", length = 64)
  private String verificationCode;

  @Column(name = "verification_reset_code", length = 64)
  private String verificationResetCode;

  private boolean enabled;

  @Column(name = "account_email")
  private String email;

  @Column(name = "account_password")
  private String password;

  @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval=true)
  @JsonSerialize(using = ProfileSerializer.class)
  private Profile profile;

  public Account() {
  }

  public String getVerificationCode() {
    return verificationCode;
  }

  public void setVerificationCode(String verificationCode) {
    this.verificationCode = verificationCode;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public String getVerificationResetCode() {
    return verificationResetCode;
  }

  public void setVerificationResetCode(String verificationResetCode) {
    this.verificationResetCode = verificationResetCode;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
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
    return enabled == account.enabled &&
      Objects.equals(id, account.id) &&
      Objects.equals(verificationCode, account.verificationCode) &&
      Objects.equals(verificationResetCode, account.verificationResetCode) &&
      Objects.equals(email, account.email) &&
      Objects.equals(password, account.password) &&
      Objects.equals(profile, account.profile);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, verificationCode, verificationResetCode, enabled, email, password, profile);
  }
}
