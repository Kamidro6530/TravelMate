package com.example.model.repositories;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);

    User findByUsername(String username);

    User findByLastName(String lastname);
}
