package com.fractgen.api.repo;

import com.fractgen.api.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Long> {
  Optional<Account> findByEmail(String email);
  boolean existsByEmail(String email);
}
