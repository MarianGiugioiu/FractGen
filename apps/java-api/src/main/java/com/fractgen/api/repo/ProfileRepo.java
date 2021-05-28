package com.fractgen.api.repo;

import com.fractgen.api.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileRepo extends JpaRepository<Profile, Long> {
  Optional<Profile> findByName(String name);
  boolean existsByName(String Name);
}
