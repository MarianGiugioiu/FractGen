package com.fractgen.api.repo;

import com.fractgen.api.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepo extends JpaRepository<Profile, Long> {
}
