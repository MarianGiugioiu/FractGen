package com.fractgen.api.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fractgen.api.serializer.AccountSerializer;
import com.fractgen.api.serializer.CommentsSerializer;
import com.fractgen.api.serializer.PostingsSerializer;
import com.fractgen.api.serializer.ProfilesSerializer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "profile")
public class Profile {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "profile_id")
  private long id;

  @Column(name = "profile_name", length = 50)
  private String name;

  @Column(name = "profile_description", length = 200)
  private String description;

  @Column(name = "profile_photo", columnDefinition = "TEXT")
  private String photo;

  @Column(name = "profile_privacy", length = 200)
  private String privacy;

  @ManyToMany()
  @JoinTable(name="follow",
    joinColumns={@JoinColumn(name="profile_id")},
    inverseJoinColumns={@JoinColumn(name="following_id")})
  @JsonSerialize(using = ProfilesSerializer.class)
  private List<Profile> following = new ArrayList<Profile>();

  @ManyToMany()
  @JoinTable(name="follow",
    joinColumns={@JoinColumn(name="following_id")},
    inverseJoinColumns={@JoinColumn(name="profile_id")})
  @JsonSerialize(using = ProfilesSerializer.class)
  private List<Profile> followed = new ArrayList<Profile>();

  @ManyToMany
  @JoinTable(
    name = "likes",
    joinColumns = @JoinColumn(name = "profile_id"),
    inverseJoinColumns = @JoinColumn(name = "posting_id")
  )
  @JsonSerialize(using = PostingsSerializer.class)
  private List<Posting> likes;

  @ManyToMany
  @JoinTable(
    name = "dislikes",
    joinColumns = @JoinColumn(name = "profile_id"),
    inverseJoinColumns = @JoinColumn(name = "posting_id")
  )
  @JsonSerialize(using = PostingsSerializer.class)
  private List<Posting> dislikes;

  @ManyToMany
  @JoinTable(
    name = "commentLikes",
    joinColumns = @JoinColumn(name = "profile_id"),
    inverseJoinColumns = @JoinColumn(name = "comment_id")
  )
  @JsonSerialize(using = CommentsSerializer.class)
  private List<Comment> likedComments;

  @ManyToMany
  @JoinTable(
    name = "commentDislikes",
    joinColumns = @JoinColumn(name = "profile_id"),
    inverseJoinColumns = @JoinColumn(name = "comment_id")
  )
  @JsonSerialize(using = CommentsSerializer.class)
  private List<Comment> dislikedComments;

  @ManyToMany
  @JoinTable(
    name = "seen",
    joinColumns = @JoinColumn(name = "profile_id"),
    inverseJoinColumns = @JoinColumn(name = "posting_id")
  )
  @JsonSerialize(using = PostingsSerializer.class)
  private List<Posting> seen;

  @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval=true)
  @JsonSerialize(using = AccountSerializer.class)
  private Account account;

  @OneToMany(mappedBy = "profile", orphanRemoval = true)
  private List<Comment> comments;

  public Profile() {
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public String getPrivacy() {
    return privacy;
  }

  public void setPrivacy(String privacy) {
    this.privacy = privacy;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  public List<Profile> getFollowing() {
    return following;
  }

  public void setFollowing(List<Profile> following) {
    this.following = following;
  }

  public List<Profile> getFollowed() {
    return followed;
  }

  public void setFollowed(List<Profile> followed) {
    this.followed = followed;
  }

  public List<Posting> getLikes() {
    return likes;
  }

  public void setLikes(List<Posting> likes) {
    this.likes = likes;
  }

  public List<Posting> getDislikes() {
    return dislikes;
  }

  public void setDislikes(List<Posting> dislikes) {
    this.dislikes = dislikes;
  }

  public List<Comment> getLikedComments() {
    return likedComments;
  }

  public void setLikedComments(List<Comment> likedComments) {
    this.likedComments = likedComments;
  }

  public List<Comment> getDislikedComments() {
    return dislikedComments;
  }

  public void setDislikedComments(List<Comment> dislikedComments) {
    this.dislikedComments = dislikedComments;
  }

  public List<Posting> getSeen() {
    return seen;
  }

  public void setSeen(List<Posting> seen) {
    this.seen = seen;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Profile)) return false;
    Profile profile = (Profile) o;
    return id == profile.id &&
      Objects.equals(name, profile.name) &&
      Objects.equals(description, profile.description) &&
      Objects.equals(photo, profile.photo) &&
      Objects.equals(privacy, profile.privacy) &&
      Objects.equals(following, profile.following) &&
      Objects.equals(followed, profile.followed) &&
      Objects.equals(likes, profile.likes) &&
      Objects.equals(dislikes, profile.dislikes) &&
      Objects.equals(likedComments, profile.likedComments) &&
      Objects.equals(dislikedComments, profile.dislikedComments) &&
      Objects.equals(seen, profile.seen) &&
      Objects.equals(account, profile.account);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, photo, privacy, following, followed, likes, dislikes, likedComments, dislikedComments, seen, account);
  }
}
