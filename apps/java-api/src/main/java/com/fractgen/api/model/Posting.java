package com.fractgen.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fractgen.api.serializer.FractalSerializer;
import com.fractgen.api.serializer.PostingsSerializer;
import com.fractgen.api.serializer.ProfileSerializer;
import com.fractgen.api.serializer.ProfilesSerializer;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "posting")
public class Posting {
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
  @Column(name = "posting_id", updatable = false, nullable = false)
  private UUID id;

  @ManyToOne
  //@JsonSerialize(using = ProfileSerializer.class)
  private Profile profile;

  @ManyToOne
  @JsonSerialize(using = FractalSerializer.class)
  private Fractal fractal;

  @Column(name = "poster_date")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime posterDate;

  @ManyToMany
  @JoinTable(
    name = "likes",
    joinColumns = @JoinColumn(name = "posting_id"),
    inverseJoinColumns = @JoinColumn(name = "profile_id")
  )
  @JsonSerialize(using = ProfilesSerializer.class)
  private List<Profile> likedBy;

  @ManyToMany
  @JoinTable(
    name = "dislikes",
    joinColumns = @JoinColumn(name = "posting_id"),
    inverseJoinColumns = @JoinColumn(name = "profile_id")
  )
  @JsonSerialize(using = ProfilesSerializer.class)
  private List<Profile> dislikedBy;

  @ManyToMany
  @JoinTable(
    name = "seen",
    joinColumns = @JoinColumn(name = "posting_id"),
    inverseJoinColumns = @JoinColumn(name = "profile_id")
  )
  @JsonSerialize(using = ProfilesSerializer.class)
  private List<Profile> seenBy;

  @OneToMany(
    mappedBy = "posting", orphanRemoval = true
  )
  private List<Comment> comments;

  public Posting() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public List<Profile> getLikedBy() {
    return likedBy;
  }

  public void setLikedBy(List<Profile> likedBy) {
    this.likedBy = likedBy;
  }

  public List<Profile> getDislikedBy() {
    return dislikedBy;
  }

  public void setDislikedBy(List<Profile> dislikedBy) {
    this.dislikedBy = dislikedBy;
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

  public List<Profile> getSeenBy() {
    return seenBy;
  }

  public void setSeenBy(List<Profile> seenBy) {
    this.seenBy = seenBy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Posting)) return false;
    Posting posting = (Posting) o;
    return Objects.equals(id, posting.id) &&
      Objects.equals(profile, posting.profile) &&
      Objects.equals(fractal, posting.fractal) &&
      Objects.equals(posterDate, posting.posterDate) &&
      Objects.equals(likedBy, posting.likedBy) &&
      Objects.equals(dislikedBy, posting.dislikedBy) &&
      Objects.equals(seenBy, posting.seenBy) &&
      Objects.equals(comments, posting.comments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, profile, fractal, posterDate, likedBy, dislikedBy, seenBy, comments);
  }
}
