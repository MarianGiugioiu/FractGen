package com.fractgen.api.repo;

import com.fractgen.api.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileRepo extends JpaRepository<Profile, Long> {
  Profile findByName(String name);
}
