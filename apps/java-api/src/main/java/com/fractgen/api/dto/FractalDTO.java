package com.fractgen.api.dto;

import java.util.Objects;
import java.util.UUID;

public class FractalDTO {
  private UUID id;
  private String dataURL;
  private String name;
  private String description;

  public FractalDTO(UUID id, String dataURL, String name, String description) {
    this.id = id;
    this.dataURL = dataURL;
    this.name = name;
    this.description = description;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getDataURL() {
    return dataURL;
  }

  public void setDataURL(String dataURL) {
    this.dataURL = dataURL;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof FractalDTO)) return false;
    FractalDTO that = (FractalDTO) o;
    return Objects.equals(id, that.id) &&
      Objects.equals(dataURL, that.dataURL) &&
      Objects.equals(name, that.name) &&
      Objects.equals(description, that.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, dataURL, name, description);
  }
}
