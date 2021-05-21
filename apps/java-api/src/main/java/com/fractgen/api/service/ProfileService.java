package com.fractgen.api.service;

import com.fractgen.api.dto.*;
import com.fractgen.api.model.*;
import com.fractgen.api.repo.ProfileRepo;
import com.fractgen.api.repo.FractalRepo;
import com.fractgen.api.repo.PostingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

  public Optional<Profile> getProfileByName (String name){
    return profileRepo.findByName(name);
  }

  public boolean existsByName (String name){
    return profileRepo.existsByName(name);
  }

  public List<ImageDTO> getAllFractals (long id) {
    List<Fractal> fractalList = fractalRepo.findByProfileId(id);
    List<ImageDTO> fractals = new ArrayList<>();
    for (Fractal fractal : fractalList) {
      fractals.add(new ImageDTO(fractal.getId(),fractal.getType(),fractal.getName(),fractal.getDataURL()));
    }
    return fractals;
  }
  public List<PostingDTO> getAllPostings (long id) {
    List<Posting> postingList = postingRepo.findByProfileId(id);
    List<PostingDTO> postings = new ArrayList<>();
    for (Posting posting : postingList) {
      postings.add(new PostingDTO(posting.getId(),posting.getFractal().getId(),posting.getFractal().getName(),posting.getFractal().getDataURL()));
    }
    return postings;
  }

  public ArrayList<NameImageClass> getAllFollowed (long id) {
    Profile profile = profileRepo.findById(id).orElse(null);
    ArrayList<NameImageClass> followed = new ArrayList<>();
    if (profile != null) {
      for (Profile follower : profile.getFollowed()) {
        followed.add(new NameImageClass(follower.getId(), follower.getName(), follower.getPhoto()));
      }
    }
    return followed;
  }

  public ArrayList<NameImageClass> getAllFollowing (long id) {
    Profile profile = profileRepo.findById(id).orElse(null);
    ArrayList<NameImageClass> following = new ArrayList<>();
    if (profile != null) {
      for (Profile follower : profile.getFollowing()) {
        following.add(new NameImageClass(follower.getId(), follower.getName(), follower.getPhoto()));
      }
    }
    return following;
  }

  public ArrayList<NameImageClass> getAllLikedPosts (long id) {
    Profile profile = profileRepo.findById(id).orElse(null);
    ArrayList<NameImageClass> likedPosts = new ArrayList<>();
    if (profile != null) {
      for (Posting likedPost : profile.getLikes()) {
        likedPosts.add(new NameImageClass(likedPost.getId(), likedPost.getFractal().getName(), likedPost.getFractal().getDataURL()));
      }
    }
    return likedPosts;
  }

  public ArrayList<NameImageClass> getAllDisikedPosts (long id) {
    Profile profile = profileRepo.findById(id).orElse(null);
    ArrayList<NameImageClass> dislikedPosts = new ArrayList<>();
    if (profile != null) {
      for (Posting dislikedPost : profile.getDislikes()) {
        dislikedPosts.add(new NameImageClass(dislikedPost.getId(), dislikedPost.getFractal().getName(), dislikedPost.getFractal().getDataURL()));
      }
    }
    return dislikedPosts;
  }

  public List<PostingWithLikesDTO> getAllUnseenPostings (long id) {
    Profile profile = profileRepo.findById(id).orElse(null);
    List<Long> followingIds = new ArrayList<>();
    for (Profile followed : profile.getFollowing()) {
      followingIds.add(followed.getId());
    }
    List<Posting> unseenPostings = new ArrayList<>();
    for (Long followingId : followingIds) {
      unseenPostings.addAll(postingRepo.findFirst2ByProfileIdAndSeenByNotContainsOrderByPosterDateDesc(followingId, profile));
    }
    List<Posting> newSeen = profile.getSeen();
    List<PostingWithLikesDTO> postings = new ArrayList<>();
    for (Posting posting : unseenPostings) {
      postings.add(new PostingWithLikesDTO(posting.getId(),posting.getProfile().getId(),posting.getProfile().getName(),posting.getFractal().getName(),posting.getFractal().getDataURL(),posting.getLikedBy().size(),posting.getDislikedBy().size()));

      //posting.setSeenBy(posting.getSeenBy().add(profile));
      newSeen.add(posting);
    }
    profile.setSeen(newSeen);
    profileRepo.save(profile);
    return postings;
  }

  public Profile addProfile (Profile profile) {
    Profile profileToSave = new Profile();
    profileToSave.setName(profile.getName());
    profileToSave.setPrivacy(profile.getPrivacy());
    profileToSave.setPhoto(profile.getPhoto());
    profileToSave.setDescription(profile.getDescription());
    profileToSave.setFollowing(profile.getFollowing());
    profileToSave.setFollowing(profile.getFollowed());
    profileToSave.setLikes(profile.getLikes());
    profileToSave.setDislikes(profile.getDislikes());
    profileToSave.setLikedComments(profile.getLikedComments());
    profileToSave.setDislikedComments(profile.getDislikedComments());
    profileToSave.setSeen(profile.getSeen());
    profileToSave.setAccount(profile.getAccount());

    return profileRepo.save(profileToSave);
  }

  public Profile addProfileWithAccount (AccountName account) {
    Profile profileToSave = new Profile();
    profileToSave.setName(account.getName());
    profileToSave.setPrivacy("{\"fractals\":true,\"followers\":true,\"following\":true,\"likes\":true,\"dislikes\":true}");
    profileToSave.setPhoto("");
    profileToSave.setDescription("");
    profileToSave.setFollowing(new ArrayList<>());
    profileToSave.setFollowing(new ArrayList<>());
    profileToSave.setLikes(new ArrayList<>());
    profileToSave.setDislikes(new ArrayList<>());
    profileToSave.setLikedComments(new ArrayList<>());
    profileToSave.setDislikedComments(new ArrayList<>());
    profileToSave.setSeen(new ArrayList<>());
    profileToSave.setAccount(account.getAccount());

    return profileRepo.save(profileToSave);
  }

  public Profile updateProfile (long id, Profile profile) {
    Profile profileToUpdate = profileRepo.findById(id).get();
    if(profile.getFollowing() != null) {
      profileToUpdate.setFollowing(profile.getFollowing());
    }
    if(profile.getFollowed() != null) {
      profileToUpdate.setFollowed(profile.getFollowed());
    }
    if(profile.getName() != null) {
      profileToUpdate.setName(profile.getName());
    }
    if(profile.getDescription() != null){
      profileToUpdate.setDescription(profile.getDescription());
    }
    if(profile.getPrivacy() != null){
      profileToUpdate.setPrivacy(profile.getPrivacy());
    }
    if(profile.getPhoto() != null){
      profileToUpdate.setPhoto(profile.getPhoto());
    }
    if(profile.getLikes() != null){
      profileToUpdate.setLikes(profile.getLikes());
    }
    if(profile.getDislikes() != null){
      profileToUpdate.setDislikes(profile.getDislikes());
    }
    if(profile.getLikedComments() != null){
      profileToUpdate.setLikedComments(profile.getLikedComments());
    }
    if(profile.getDislikedComments() != null){
      profileToUpdate.setDislikedComments(profile.getDislikedComments());
    }
    if(profile.getSeen() != null){
      profileToUpdate.setSeen(profile.getSeen());
    }
    if(profile.getAccount() != null) {
      profileToUpdate.setAccount(profile.getAccount());
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
