package com.fractgen.api.repo;

import com.fractgen.api.model.Account;
import com.fractgen.api.model.Fractal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepo extends JpaRepository<Account, Long> {
}
