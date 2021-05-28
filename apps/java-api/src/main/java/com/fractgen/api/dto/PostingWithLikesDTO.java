package com.fractgen.api.dto;

import java.util.Objects;

public class PostingWithLikesDTO {
  private long id;
  private long idCreator;
  private String creator;
  private String name;
  private String image;
  private long likes;
  private long dislikes;

  public PostingWithLikesDTO(long id, long idCreator, String creator, String name, String image, long likes, long dislikes) {
    this.id = id;
    this.idCreator = idCreator;
    this.creator = creator;
    this.name = name;
    this.image = image;
    this.likes = likes;
    this.dislikes = dislikes;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getIdCreator() {
    return idCreator;
  }

  public void setIdCreator(long idCreator) {
    this.idCreator = idCreator;
  }

  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
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

  public long getLikes() {
    return likes;
  }

  public void setLikes(long likes) {
    this.likes = likes;
  }

  public long getDislikes() {
    return dislikes;
  }

  public void setDislikes(long dislikes) {
    this.dislikes = dislikes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PostingWithLikesDTO)) return false;
    PostingWithLikesDTO that = (PostingWithLikesDTO) o;
    return id == that.id &&
      idCreator == that.idCreator &&
      likes == that.likes &&
      dislikes == that.dislikes &&
      Objects.equals(creator, that.creator) &&
      Objects.equals(name, that.name) &&
      Objects.equals(image, that.image);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, idCreator, creator, name, image, likes, dislikes);
  }
}
