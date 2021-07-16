package com.fractgen.api.repo;

import com.fractgen.api.model.Fractal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FractalRepo extends JpaRepository<Fractal, UUID> {
  List<Fractal> findByProfileId(UUID id);
}
