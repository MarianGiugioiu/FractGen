package com.fractgen.api.repo;

import com.fractgen.api.model.Fractal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FractalRepo extends JpaRepository<Fractal, Long> {
  List<Fractal> findByAccountId(long id);
}
