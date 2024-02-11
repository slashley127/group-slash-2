package org.launchcode.roomranger.data;

import org.launchcode.roomranger.models.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends CrudRepository<Manager, Integer> {
}
