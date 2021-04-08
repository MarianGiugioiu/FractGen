package com.fractgen.api.service;

import com.fractgen.api.model.Comment;
import com.fractgen.api.model.Posting;
import com.fractgen.api.repo.CommentRepo;
import com.fractgen.api.repo.PostingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostingService {
  @Autowired
  PostingRepo postingRepo;
  @Autowired
  CommentRepo commentRepo;

  public List<Posting> getAllPostings () {
    return postingRepo.findAll();
  }

  public Optional<Posting> getPostingById (long id){
    return postingRepo.findById(id);
  }

  public List<Comment> getAllComments (long id) {
    return commentRepo.findByPostingId(id);
  }

  public Posting addPosting (Posting posting) {
    Posting postingToSave = new Posting();
    postingToSave.setPosterDate(posting.getPosterDate());
    postingToSave.setFractal(posting.getFractal());
    postingToSave.setProfile(posting.getProfile());
    postingToSave.setLikes(posting.getLikes());

    return postingRepo.save(postingToSave);
  }

  public Posting updatePosting (long id, Posting posting) {
    Posting postingToUpdate = postingRepo.findById(id).get();
    if(posting.getPosterDate() != null) {
      postingToUpdate.setPosterDate(posting.getPosterDate());
    }
    if(posting.getFractal() != null) {
      postingToUpdate.setFractal(posting.getFractal());
    }
    if(posting.getProfile() != null){
      postingToUpdate.setProfile(posting.getProfile());
    }
    postingToUpdate.setLikes(posting.getLikes());

    return postingRepo.save(postingToUpdate);
  }

  public void deletePosting (long id) {
    postingRepo.deleteById(id);
  }

  public boolean postingExists (long id) {
    return postingRepo.existsById(id);
  }
}
