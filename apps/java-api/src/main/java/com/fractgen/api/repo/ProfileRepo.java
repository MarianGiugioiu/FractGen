package com.fractgen.api.repo;

import com.fractgen.api.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfileRepo extends JpaRepository<Profile, UUID> {
  Optional<Profile> findByName(String name);
  boolean existsByName(String Name);
}
