package com.crud.crud.repository;

import org.springframework.data.repository.CrudRepository;
import com.crud.crud.model.User;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    
    Optional<User> findByUsername(String username); // SELECT * FROM users WHERE username = ?
}