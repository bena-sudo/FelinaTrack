package com.pfc.felinatrack_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import io.micrometer.common.lang.NonNull;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.pfc.felinatrack_back.model.db.CatDb;

public interface CatRepository extends JpaRepository<CatDb, Long>, JpaSpecificationExecutor<CatDb>{
    @NonNull Page<CatDb> findAll(@NonNull Pageable pageable);
    @NonNull List<CatDb> findAll(@NonNull Sort sort);
}
