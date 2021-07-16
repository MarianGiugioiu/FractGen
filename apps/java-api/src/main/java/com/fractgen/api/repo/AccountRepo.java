package com.fractgen.api.repo;

import com.fractgen.api.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepo extends JpaRepository<Account, UUID> {
  Optional<Account> findByEmail(String email);
  boolean existsByEmail(String email);
  @Query("SELECT a FROM Account a WHERE a.verificationCode = ?1")
  public Account findByVerificationCode(String code);
  @Query("SELECT a FROM Account a WHERE a.verificationResetCode = ?1")
  public Account findByVerificationResetCode(String code);
}
