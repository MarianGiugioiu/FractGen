package com.fractgen.api.service;

import com.fractgen.api.model.Posting;
import com.fractgen.api.model.Profile;
import com.fractgen.api.model.Fractal;
import com.fractgen.api.repo.ProfileRepo;
import com.fractgen.api.repo.FractalRepo;
import com.fractgen.api.repo.PostingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
  @Autowired
  ProfileRepo profileRepo;
  @Autowired
  FractalRepo fractalRepo;
  @Autowired
  PostingRepo postingRepo;

  public List<Profile> getAllProfiles () {
    return profileRepo.findAll();
  }

  public Optional<Profile> getProfileById (long id){
    return profileRepo.findById(id);
  }

  public List<Fractal> getAllFractals (long id) {
    return fractalRepo.findByProfileId(id);
  }
  public List<Posting> getAllPostings (long id) {
    return postingRepo.findByProfileId(id);
  }

  public Profile addProfile (Profile profile) {
    Profile profileToSave = new Profile();
    profileToSave.setName(profile.getName());
    profileToSave.setDescription(profile.getDescription());
    profileToSave.setFollowing(profile.getFollowing());

    return profileRepo.save(profileToSave);
  }

  public Profile updateProfile (long id, Profile profile) {
    Profile profileToUpdate = profileRepo.findById(id).get();
    if(profile.getFollowing() != null) {
      profileToUpdate.setFollowing(profile.getFollowing());
    }
    if(profile.getName() != null) {
      profileToUpdate.setName(profile.getName());
    }
    if(profile.getDescription() != null){
      profileToUpdate.setDescription(profile.getDescription());
    }

    return profileRepo.save(profileToUpdate);
  }

  public void deleteProfile (long id) {
    profileRepo.deleteById(id);
  }

  public boolean profileExists (long id) {
    return profileRepo.existsById(id);
  }
}
