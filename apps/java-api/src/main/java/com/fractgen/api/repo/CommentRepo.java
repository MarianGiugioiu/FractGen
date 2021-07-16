package com.fractgen.api.repo;

import com.fractgen.api.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepo extends JpaRepository<Comment, UUID> {
  List<Comment> findByPostingId(UUID id);
}
