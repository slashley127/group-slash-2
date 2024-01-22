package org.launchcode.roomranger.data;

import org.launchcode.roomranger.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
//    User findByUserName(String username);
}
