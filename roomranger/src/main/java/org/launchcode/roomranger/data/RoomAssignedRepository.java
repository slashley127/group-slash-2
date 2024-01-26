package org.launchcode.roomranger.data;

import org.launchcode.roomranger.models.RoomAssigned;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomAssignedRepository extends CrudRepository<RoomAssigned, Integer> {
}
