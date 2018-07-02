package com.washinSystems.homepage.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.washinSystems.homepage.domain.CodeRole;

public interface CodeRoleRepository extends JpaRepository<CodeRole, Integer> {
	Page<CodeRole> findAll(Pageable pageable);
}