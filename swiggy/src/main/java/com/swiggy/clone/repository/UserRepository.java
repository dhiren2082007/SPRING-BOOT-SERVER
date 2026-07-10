package com.swiggy.clone.repository;

import com.swiggy.clone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
