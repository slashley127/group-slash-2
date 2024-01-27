package org.launchcode.roomranger.data;

import org.launchcode.roomranger.models.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends CrudRepository<Manager, Integer> {
    //List<Manager> findbyId();

}
