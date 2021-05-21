package com.fractgen.api.dto;

import java.util.Objects;

public class PostingDTO {
  private long id;
  private long idFractal;
  private String name;
  private String image;

  public PostingDTO(long id, long idFractal, String name, String image) {
    this.id = id;
    this.idFractal = idFractal;
    this.name = name;
    this.image = image;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getIdFractal() {
    return idFractal;
  }

  public void setIdFractal(long idFractal) {
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
    return id == that.id &&
      idFractal == that.idFractal &&
      Objects.equals(name, that.name) &&
      Objects.equals(image, that.image);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, idFractal, name, image);
  }
}
