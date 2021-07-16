package com.fractgen.api.controller;

import com.fractgen.api.dto.*;
import com.fractgen.api.exception.ResourceNotFoundException;
import com.fractgen.api.model.*;
import com.fractgen.api.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/profile")
public class ProfileController {
  @Autowired
  ProfileService profileService;

  @GetMapping(value = {"", "/"})
  public ResponseEntity<List<Profile>> getAllProfiles () {
    List<Profile> profileList = profileService.getAllProfiles();
    return new ResponseEntity<>(profileList, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Profile> getProfileById (@PathVariable("id") UUID id){
    Profile profile = profileService.getProfileById(id)
      .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "No Profile found with this ID", new ResourceNotFoundException()
      ));
    return new ResponseEntity<>(profile, HttpStatus.OK);
  }

  @GetMapping(value = "/name/{name}")
  public ResponseEntity<UUID> getProfileByName (@PathVariable("name") String name){
    Profile profile = profileService.getProfileByName(name)
      .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "No Profile found with this ID", new ResourceNotFoundException()
      ));
    return new ResponseEntity<>(profile.getId(), HttpStatus.OK);
  }

  @GetMapping(value = "/exists/name/{name}")
  public ResponseEntity<Boolean> existsByName (@PathVariable("name") String name){
    boolean exists = profileService.existsByName(name);
    return new ResponseEntity<>(exists, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}/fractals")
  public ResponseEntity<List<ImageDTO>> getFractalsByProfileId (@PathVariable("id") UUID id){
    List<ImageDTO> fractalList = profileService.getAllFractals(id);
    return new ResponseEntity<>( fractalList, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}/postings")
  public ResponseEntity<List<PostingDTO>> getPostingsByProfileId (@PathVariable("id") UUID id){
    List<PostingDTO> postingList = profileService.getAllPostings(id);
    return new ResponseEntity<>( postingList, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}/followed")
  public ResponseEntity<List<NameImageClass>> getFollowersByProfileId (@PathVariable("id") UUID id){
    List<NameImageClass> followedList = profileService.getAllFollowed(id);
    return new ResponseEntity<>( followedList, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}/following")
  public ResponseEntity<List<NameImageClass>> getFollowingByProfileId (@PathVariable("id") UUID id){
    List<NameImageClass> followingList = profileService.getAllFollowing(id);
    return new ResponseEntity<>( followingList, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}/likes")
  public ResponseEntity<List<NameImageClass>> getLikesByProfileId (@PathVariable("id") UUID id){
    List<NameImageClass> likeList = profileService.getAllLikedPosts(id);
    return new ResponseEntity<>( likeList, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}/dislikes")
  public ResponseEntity<List<NameImageClass>> getDislikesByProfileId (@PathVariable("id") UUID id){
    List<NameImageClass> dislikeList = profileService.getAllDisikedPosts(id);
    return new ResponseEntity<>( dislikeList, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}/unseen")
  public ResponseEntity<List<PostingWithLikesDTO>> getUnseenPostings (@PathVariable("id") UUID id){
    List<PostingWithLikesDTO> unseenPostings = profileService.getAllUnseenPostings(id);
    return new ResponseEntity<>( unseenPostings, HttpStatus.OK);
  }

  @PostMapping(value = {"", "/"})
  public ResponseEntity<Profile> addProfile (@RequestBody Profile profile) {
    Profile savedProfile = profileService.addProfile(profile);
    return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
  }

  @PostMapping(value = {"", "/name"})
  public ResponseEntity<Profile> addProfileWithAccount (@RequestBody AccountName account) {
    Profile savedProfile = profileService.addProfileWithAccount(account);
    return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
  }

  @PutMapping(value = ("/{id}"))
  public ResponseEntity<Profile> updateProfile (@PathVariable("id") UUID id,
                                                @RequestBody Profile profile) {
    if (profileService.profileExists(id)) {
      Profile updatedProfile = profileService.updateProfile(id, profile);
      return new ResponseEntity<>(updatedProfile, HttpStatus.ACCEPTED);
    } else {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Cannot update non-existing Profile", new ResourceNotFoundException()
      );
    }
  }

  @DeleteMapping(value = ("/{id}"))
  public ResponseEntity<HttpStatus> deleteProfile (@PathVariable("id") UUID id) {
    if (profileService.profileExists(id)) {
      profileService.deleteProfile(id);
      return new ResponseEntity<>(HttpStatus.ACCEPTED);
    } else {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Cannot delete non-existing Profile", new ResourceNotFoundException()
      );
    }
  }
}
