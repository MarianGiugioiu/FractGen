package com.fractgen.api.dto;

import java.util.Objects;
import java.util.UUID;

public class IdClass {
  private UUID id;

  public IdClass(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof IdClass)) return false;
    IdClass idClass = (IdClass) o;
    return Objects.equals(id, idClass.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
