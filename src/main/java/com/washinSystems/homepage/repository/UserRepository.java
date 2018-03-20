package com.washinSystems.homepage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.washinSystems.homepage.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
}