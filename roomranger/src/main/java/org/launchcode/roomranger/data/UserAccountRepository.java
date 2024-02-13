package org.launchcode.roomranger.data;

import org.launchcode.roomranger.models.User;
import org.launchcode.roomranger.models.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Integer> {

    UserAccount findByUsername(String username);


}
