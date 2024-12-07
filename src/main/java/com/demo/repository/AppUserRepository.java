package com.demo.repository;

import com.demo.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsernameOrEmailId(String username, String emailId);

    Optional<AppUser> findByUsername(String username);
}