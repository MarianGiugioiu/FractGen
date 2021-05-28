package com.fractgen.api.controller;

import com.fractgen.api.exception.ResourceNotFoundException;
import com.fractgen.api.model.Comment;
import com.fractgen.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
  @Autowired
  CommentService commentService;

  @GetMapping(value = {"", "/"})
  public ResponseEntity<List<Comment>> getAllComments () {
    List<Comment> commentList = commentService.getAllComments();
    return new ResponseEntity<>(commentList, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Comment> getCommentById (@PathVariable("id") long id){
    Comment comment = commentService.getCommentById(id)
      .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "No Comment found with this ID", new ResourceNotFoundException()
      ));
    return new ResponseEntity<>(comment, HttpStatus.OK);
  }

  @PostMapping(value = {"", "/"})
  public ResponseEntity<Comment> addComment (@RequestBody Comment comment) {
    Comment savedComment = commentService.addComment(comment);
    return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
  }

  @PutMapping(value = ("/{id}"))
  public ResponseEntity<Comment> updateComment (@PathVariable("id") long id,
                                                @RequestBody Comment comment) {
    if (commentService.commentExists(id)) {
      Comment updatedComment = commentService.updateComment(id, comment);
      return new ResponseEntity<>(updatedComment, HttpStatus.ACCEPTED);
    } else {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Cannot update non-existing Comment", new ResourceNotFoundException()
      );
    }
  }

  @DeleteMapping(value = ("/{id}"))
  public ResponseEntity<HttpStatus> deleteComment (@PathVariable("id") long id) {
    if (commentService.commentExists(id)) {
      commentService.deleteComment(id);
      return new ResponseEntity<>(HttpStatus.ACCEPTED);
    } else {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Cannot delete non-existing Comment", new ResourceNotFoundException()
      );
    }
  }

}
