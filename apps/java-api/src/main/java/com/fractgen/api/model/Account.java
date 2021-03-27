package com.fractgen.api.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "account")
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "account_id")
  private long id;

  public Account() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Account)) return false;
    Account account = (Account) o;
    return id == account.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
