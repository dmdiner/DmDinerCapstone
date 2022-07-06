package com.ZooFood.Diner_David_ZooFood_CaseStudy.repository;

import com.ZooFood.Diner_David_ZooFood_CaseStudy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);
}
