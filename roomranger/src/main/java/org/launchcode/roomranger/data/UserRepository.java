package org.launchcode.roomranger.data;

import org.launchcode.roomranger.models.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository< User, Long> {
    User findByUsername(String username);
}