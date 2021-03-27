package com.fractgen.api.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "fractal")
public class Fractal {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "fractal_id")
  private long id;

  @Column(name = "fractal_type", length = 50)
  private String type;

  @Column(name = "fractal_name", length = 50)
  private String name;

  @Column(name = "fractal_description", length = 200)
  private String description;

  @Column(name = "last_modified")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime lastModified;

  @Column(name = "fractal_options", columnDefinition = "MEDIUMTEXT")
  private String options;

  @Column(name = "fractal_data_url", columnDefinition = "MEDIUMTEXT")
  private String dataURL;

  @ManyToOne
  private Account account;

  public Fractal() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDateTime getLastModified() {
    return lastModified;
  }

  public void setLastModified(LocalDateTime lastModified) {
    this.lastModified = lastModified;
  }

  public String getOptions() {
    return options;
  }

  public void setOptions(String options) {
    this.options = options;
  }

  public String getDataURL() {
    return dataURL;
  }

  public void setDataURL(String dataURL) {
    this.dataURL = dataURL;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Fractal)) return false;
    Fractal fractal = (Fractal) o;
    return id == fractal.id &&
      Objects.equals(type, fractal.type) &&
      Objects.equals(name, fractal.name) &&
      Objects.equals(description, fractal.description) &&
      Objects.equals(lastModified, fractal.lastModified) &&
      Objects.equals(options, fractal.options) &&
      Objects.equals(dataURL, fractal.dataURL) &&
      Objects.equals(account, fractal.account);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name, description, lastModified, options, dataURL, account);
  }
}
