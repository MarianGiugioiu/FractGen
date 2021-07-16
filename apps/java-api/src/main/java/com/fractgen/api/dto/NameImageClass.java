package com.fractgen.api.dto;

import java.util.Objects;
import java.util.UUID;

public class NameImageClass {
  private UUID id;
  private String name;
  private String image;

  public NameImageClass(UUID id, String name, String image) {
    this.id = id;
    this.name = name;
    this.image = image;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof NameImageClass)) return false;
    NameImageClass that = (NameImageClass) o;
    return Objects.equals(id, that.id) &&
      Objects.equals(name, that.name) &&
      Objects.equals(image, that.image);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, image);
  }
}
