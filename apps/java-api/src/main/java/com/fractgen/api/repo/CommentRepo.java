package com.fractgen.api.repo;

import com.fractgen.api.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {
  List<Comment> findByPostingId(long id);
}
