package com.fractgen.api.controller;

import com.fractgen.api.exception.ResourceNotFoundException;
import com.fractgen.api.model.Posting;
import com.fractgen.api.model.Profile;
import com.fractgen.api.model.Fractal;
import com.fractgen.api.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
  public ResponseEntity<Profile> getProfileById (@PathVariable("id") long id){
    Profile profile = profileService.getProfileById(id)
      .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "No Profile found with this ID", new ResourceNotFoundException()
      ));
    return new ResponseEntity<>(profile, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}/fractals")
  public ResponseEntity<List<Fractal>> getFractalsByProfileId (@PathVariable("id") long id){
    List<Fractal> fractalList = profileService.getAllFractals(id);
    return new ResponseEntity<>( fractalList, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}/postings")
  public ResponseEntity<List<Posting>> getPostingsByProfileId (@PathVariable("id") long id){
    List<Posting> postingList = profileService.getAllPostings(id);
    return new ResponseEntity<>( postingList, HttpStatus.OK);
  }

  @PostMapping(value = {"", "/"})
  public ResponseEntity<Profile> addProfile (@RequestBody Profile profile) {
    Profile savedProfile = profileService.addProfile(profile);
    return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
  }

  @PutMapping(value = ("/{id}"))
  public ResponseEntity<Profile> updateProfile (@PathVariable("id") long id,
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
  public ResponseEntity<HttpStatus> deleteProfile (@PathVariable("id") long id) {
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
