package com.fractgen.api.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fractgen.api.serializer.ProfileSerializer;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "fractal")
public class Fractal {
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
  @Column(name = "fractal_id", updatable = false, nullable = false)
  private UUID id;

  @Column(name = "fractal_type", length = 50)
  private String type;

  @Column(name = "fractal_status")
  private boolean status;

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
  @JsonSerialize(using = ProfileSerializer.class)
  private Profile profile;

  @OneToMany(mappedBy = "fractal", orphanRemoval = true)
  private List<Posting> postings;

  public Fractal() {
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

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
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

  public Profile getProfile() {
    return profile;
  }

  public void setProfile(Profile profile) {
    this.profile = profile;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Fractal)) return false;
    Fractal fractal = (Fractal) o;
    return status == fractal.status &&
      Objects.equals(id, fractal.id) &&
      Objects.equals(type, fractal.type) &&
      Objects.equals(name, fractal.name) &&
      Objects.equals(description, fractal.description) &&
      Objects.equals(lastModified, fractal.lastModified) &&
      Objects.equals(options, fractal.options) &&
      Objects.equals(dataURL, fractal.dataURL) &&
      Objects.equals(profile, fractal.profile) &&
      Objects.equals(postings, fractal.postings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, status, name, description, lastModified, options, dataURL, profile, postings);
  }
}
