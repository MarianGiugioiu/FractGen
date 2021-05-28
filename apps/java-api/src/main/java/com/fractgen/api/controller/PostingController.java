package com.fractgen.api.controller;

import com.fractgen.api.exception.ResourceNotFoundException;
import com.fractgen.api.model.Comment;
import com.fractgen.api.model.Fractal;
import com.fractgen.api.model.Posting;
import com.fractgen.api.service.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/posting")
public class PostingController {
  @Autowired
  PostingService postingService;

  @GetMapping(value = {"", "/"})
  public ResponseEntity<List<Posting>> getAllPostings () {
    List<Posting> postingList = postingService.getAllPostings();
    return new ResponseEntity<>(postingList, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Posting> getPostingById (@PathVariable("id") long id){
    Posting posting = postingService.getPostingById(id)
      .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "No Posting found with this ID", new ResourceNotFoundException()
      ));
    return new ResponseEntity<>(posting, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}/comments")
  public ResponseEntity<List<Comment>> getCommentsByPostingId (@PathVariable("id") long id){
    List<Comment> commentList = postingService.getAllComments(id);
    return new ResponseEntity<>( commentList, HttpStatus.OK);
  }

  @PostMapping(value = {"", "/"})
  public ResponseEntity<Posting> addPosting (@RequestBody Posting posting) {
    Posting savedPosting = postingService.addPosting(posting);
    return new ResponseEntity<>(savedPosting, HttpStatus.CREATED);
  }

  @PutMapping(value = ("/{id}"))
  public ResponseEntity<Posting> updatePosting (@PathVariable("id") long id,
                                                @RequestBody Posting posting) {
    if (postingService.postingExists(id)) {
      Posting updatedPosting = postingService.updatePosting(id, posting);
      return new ResponseEntity<>(updatedPosting, HttpStatus.ACCEPTED);
    } else {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Cannot update non-existing Posting", new ResourceNotFoundException()
      );
    }
  }

  @DeleteMapping(value = ("/{id}"))
  public ResponseEntity<HttpStatus> deletePosting (@PathVariable("id") long id) {
    if (postingService.postingExists(id)) {
      postingService.deletePosting(id);
      return new ResponseEntity<>(HttpStatus.ACCEPTED);
    } else {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Cannot delete non-existing Posting", new ResourceNotFoundException()
      );
    }
  }

}
