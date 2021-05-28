package com.fractgen.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fractgen.api.serializer.PostingSerializer;
import com.fractgen.api.serializer.ProfileSerializer;
import com.fractgen.api.serializer.ProfilesSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "comment")
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "comment_id")
  private long id;

  @Column(name = "creator", length = 50)
  private String createdBy;

  @Column(name = "comment_text", length = 200)
  private String text;

  @Column(name = "last_modified")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime lastModified;

  @Column(name = "edited", nullable = false)
  private boolean edited;

  @ManyToOne
  @JsonSerialize(using = ProfileSerializer.class)
  private Profile profile;

  @ManyToOne
  @JsonSerialize(using = PostingSerializer.class)
  private Posting posting;

  @ManyToMany
  @JoinTable(
    name = "commentLikes",
    joinColumns = @JoinColumn(name = "comment_id"),
    inverseJoinColumns = @JoinColumn(name = "profile_id")
  )
  @JsonSerialize(using = ProfilesSerializer.class)
  private List<Profile> likedBy;

  @ManyToMany
  @JoinTable(
    name = "commentDislikes",
    joinColumns = @JoinColumn(name = "comment_id"),
    inverseJoinColumns = @JoinColumn(name = "profile_id")
  )
  @JsonSerialize(using = ProfilesSerializer.class)
  private List<Profile> dislikedBy;

  public Comment() {
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Posting getPosting() {
    return posting;
  }

  public void setPosting(Posting posting) {
    this.posting = posting;
  }

  public LocalDateTime getLastModified() {
    return lastModified;
  }

  public void setLastModified(LocalDateTime lastModified) {
    this.lastModified = lastModified;
  }

  public boolean isEdited() {
    return edited;
  }

  public void setEdited(boolean edited) {
    this.edited = edited;
  }

  public Profile getProfile() {
    return profile;
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

  public void setProfile(Profile profile) {
    this.profile = profile;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Comment)) return false;
    Comment comment = (Comment) o;
    return id == comment.id &&
      edited == comment.edited &&
      Objects.equals(createdBy, comment.createdBy) &&
      Objects.equals(text, comment.text) &&
      Objects.equals(lastModified, comment.lastModified) &&
      Objects.equals(profile, comment.profile) &&
      Objects.equals(posting, comment.posting) &&
      Objects.equals(likedBy, comment.likedBy) &&
      Objects.equals(dislikedBy, comment.dislikedBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdBy, text, lastModified, edited, profile, posting, likedBy, dislikedBy);
  }
}
