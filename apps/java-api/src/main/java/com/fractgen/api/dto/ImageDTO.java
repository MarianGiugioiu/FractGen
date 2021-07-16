package com.fractgen.api.dto;

import java.util.Objects;
import java.util.UUID;

public class ImageDTO {
  private UUID id;
  private String type;
  private String name;
  private String image;

  public ImageDTO(UUID id, String type, String name, String image) {
    this.id = id;
    this.type = type;
    this.name = name;
    this.image = image;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
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
    if (!(o instanceof ImageDTO)) return false;
    ImageDTO imageDTO = (ImageDTO) o;
    return Objects.equals(id, imageDTO.id) &&
      Objects.equals(type, imageDTO.type) &&
      Objects.equals(name, imageDTO.name) &&
      Objects.equals(image, imageDTO.image);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name, image);
  }
}
