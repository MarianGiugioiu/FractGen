package com.fractgen.api.service;

import com.fractgen.api.model.Comment;
import com.fractgen.api.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
  @Autowired
  CommentRepo commentRepo;

  public List<Comment> getAllComments () {
    return commentRepo.findAll();
  }

  public Optional<Comment> getCommentById (long id){
    return commentRepo.findById(id);
  }

  public Comment addComment (Comment comment) {
    Comment commentToSave = new Comment();
    commentToSave.setText(comment.getText());
    commentToSave.setLastModified(comment.getLastModified());
    commentToSave.setEdited(comment.isEdited());
    commentToSave.setProfile(comment.getProfile());
    commentToSave.setPosting(comment.getPosting());
    commentToSave.setLikedBy(comment.getLikedBy());
    commentToSave.setDislikedBy(comment.getDislikedBy());
    commentToSave.setCreatedBy(comment.getCreatedBy());
    return commentRepo.save(commentToSave);
  }

  public Comment updateComment (long id, Comment comment) {
    Comment commentToUpdate = commentRepo.findById(id).get();
    if(comment.getText() != null) {
      commentToUpdate.setText(comment.getText());
    }
    if(comment.getLastModified() != null) {
      commentToUpdate.setLastModified(comment.getLastModified());
    }
    if(comment.getPosting() != null){
      commentToUpdate.setPosting(comment.getPosting());
    }
    commentToUpdate.setEdited(comment.isEdited());
    if(comment.getProfile() != null){
      commentToUpdate.setProfile(comment.getProfile());
    }
    if(comment.getLikedBy() != null){
      commentToUpdate.setLikedBy(comment.getLikedBy());
    }
    if(comment.getDislikedBy() != null){
      commentToUpdate.setDislikedBy(comment.getDislikedBy());
    }
    if(comment.getCreatedBy() != null){
      commentToUpdate.setCreatedBy(comment.getCreatedBy());
    }

    return commentRepo.save(commentToUpdate);
  }

  public void deleteComment (long id) {
    commentRepo.deleteById(id);
  }

  public boolean commentExists (long id) {
    return commentRepo.existsById(id);
  }
}
