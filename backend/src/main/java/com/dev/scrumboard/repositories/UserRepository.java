package com.dev.scrumboard.repositories;

import com.dev.scrumboard.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
