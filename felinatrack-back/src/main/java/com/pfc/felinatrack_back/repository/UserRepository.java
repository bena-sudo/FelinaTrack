package com.pfc.felinatrack_back.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.pfc.felinatrack_back.model.db.UserDb;

import io.micrometer.common.lang.NonNull;


public interface UserRepository extends JpaRepository<UserDb, Long>, JpaSpecificationExecutor<UserDb>{
    Optional<UserDb> findByEmail(String email);
    boolean existsByEmail(String email);

    @NonNull Page<UserDb> findAll(@NonNull Pageable pageable);
}
