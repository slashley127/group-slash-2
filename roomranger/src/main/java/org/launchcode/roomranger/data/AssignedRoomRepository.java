package org.launchcode.roomranger.data;

import org.launchcode.roomranger.models.AssignedRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignedRoomRepository extends CrudRepository<AssignedRoom, Integer> {
}
