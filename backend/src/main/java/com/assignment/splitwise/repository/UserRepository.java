package com.assignment.splitwise.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.splitwise.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	Optional<User> findByEmail(String email);
	List<User> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name, String email);
}
