package com.fractgen.api.repo;

import com.fractgen.api.model.Posting;
import com.fractgen.api.model.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface PostingRepo extends JpaRepository<Posting, Long> {
  List<Posting> findByProfileId(long id);
  List<Posting> findFirst2ByProfileIdAndSeenByNotContainsOrderByPosterDateDesc(long id, Profile profile);

}
