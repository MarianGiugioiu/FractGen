package com.fractgen.api.repo;

import com.fractgen.api.model.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingRepo extends JpaRepository<Posting, Long> {
  List<Posting> findByProfileId(long id);
}
