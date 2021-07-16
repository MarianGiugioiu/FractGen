package com.fractgen.api.dto;

import java.util.Objects;
import java.util.UUID;

public class PostingDTO {
  private UUID id;
  private UUID idFractal;
  private String name;
  private String image;

  public PostingDTO(UUID id, UUID idFractal, String name, String image) {
    this.id = id;
    this.idFractal = idFractal;
    this.name = name;
    this.image = image;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getIdFractal() {
    return idFractal;
  }

  public void setIdFractal(UUID idFractal) {
    this.idFractal = idFractal;
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
    if (!(o instanceof PostingDTO)) return false;
    PostingDTO that = (PostingDTO) o;
    return Objects.equals(id, that.id) &&
      Objects.equals(idFractal, that.idFractal) &&
      Objects.equals(name, that.name) &&
      Objects.equals(image, that.image);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, idFractal, name, image);
  }
}
