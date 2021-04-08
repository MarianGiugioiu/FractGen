package com.fractgen.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fractgen.api.serializer.FractalSerializer;
import com.fractgen.api.serializer.ProfileSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "posting")
public class Posting {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "posting_id")
  private long id;

  @ManyToOne
  @JsonSerialize(using = ProfileSerializer.class)
  private Profile profile;

  @OneToOne
  @JsonSerialize(using = FractalSerializer.class)
  private Fractal fractal;

  @Column(name = "poster_date")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime posterDate;

  @Column(name = "posting_likes", nullable = false)
  private long likes;


  public Posting() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getLikes() {
    return likes;
  }

  public void setLikes(long likes) {
    this.likes = likes;
  }

  public Profile getProfile() {
    return profile;
  }

  public void setProfile(Profile profile) {
    this.profile = profile;
  }

  public Fractal getFractal() {
    return fractal;
  }

  public void setFractal(Fractal fractal) {
    this.fractal = fractal;
  }

  public LocalDateTime getPosterDate() {
    return posterDate;
  }

  public void setPosterDate(LocalDateTime posterDate) {
    this.posterDate = posterDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Posting)) return false;
    Posting posting = (Posting) o;
    return id == posting.id &&
      likes == posting.likes &&
      Objects.equals(profile, posting.profile) &&
      Objects.equals(fractal, posting.fractal) &&
      Objects.equals(posterDate, posting.posterDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, profile, fractal, posterDate, likes);
  }
}
