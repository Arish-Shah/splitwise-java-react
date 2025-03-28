package com.assignment.splitwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.splitwise.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
