package org.launchcode.roomranger.data;

import org.launchcode.roomranger.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    // Add this method to check if a user with the given username already exists
    boolean existsByUsername(String username);

    // You can also add other query methods if needed
}