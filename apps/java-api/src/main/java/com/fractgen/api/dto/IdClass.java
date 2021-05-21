package com.fractgen.api.dto;

import java.util.Objects;

public class IdClass {
  private long id;

  public IdClass(long id) {
    this.id = id;
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
    if (!(o instanceof IdClass)) return false;
    IdClass idClass = (IdClass) o;
    return id == idClass.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
