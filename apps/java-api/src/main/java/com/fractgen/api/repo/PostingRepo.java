package com.fractgen.api.repo;

import com.fractgen.api.model.Posting;
import com.fractgen.api.model.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

public interface PostingRepo extends JpaRepository<Posting, UUID> {
  List<Posting> findByProfileId(UUID id);
  List<Posting> findFirst2ByProfileIdAndSeenByNotContainsOrderByPosterDateDesc(UUID id, Profile profile);

}
