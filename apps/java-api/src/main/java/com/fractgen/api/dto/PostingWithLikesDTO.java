package com.fractgen.api.dto;

import java.util.Objects;
import java.util.UUID;

public class PostingWithLikesDTO {
  private UUID id;
  private UUID idCreator;
  private String creator;
  private String name;
  private String image;
  private long likes;
  private long dislikes;

  public PostingWithLikesDTO(UUID id, UUID idCreator, String creator, String name, String image, long likes, long dislikes) {
    this.id = id;
    this.idCreator = idCreator;
    this.creator = creator;
    this.name = name;
    this.image = image;
    this.likes = likes;
    this.dislikes = dislikes;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getIdCreator() {
    return idCreator;
  }

  public void setIdCreator(UUID idCreator) {
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
    return likes == that.likes &&
      dislikes == that.dislikes &&
      Objects.equals(id, that.id) &&
      Objects.equals(idCreator, that.idCreator) &&
      Objects.equals(creator, that.creator) &&
      Objects.equals(name, that.name) &&
      Objects.equals(image, that.image);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, idCreator, creator, name, image, likes, dislikes);
  }
}
