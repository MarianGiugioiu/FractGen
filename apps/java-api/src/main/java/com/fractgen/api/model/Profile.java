package com.fractgen.api.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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

  @ManyToMany()
  @JoinTable(name="follow",
    joinColumns={@JoinColumn(name="profile_id")},
    inverseJoinColumns={@JoinColumn(name="following_id")})
  @JsonSerialize(using = ProfilesSerializer.class)
  private List<Profile> following = new ArrayList<Profile>();

  public Profile() {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Profile)) return false;
    Profile profile = (Profile) o;
    return id == profile.id &&
      Objects.equals(name, profile.name) &&
      Objects.equals(description, profile.description) &&
      Objects.equals(following, profile.following);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, following);
  }
}
