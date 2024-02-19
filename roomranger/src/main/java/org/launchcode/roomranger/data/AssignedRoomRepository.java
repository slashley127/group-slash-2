package org.launchcode.roomranger.data;

import org.launchcode.roomranger.models.AssignedRoom;
import org.launchcode.roomranger.models.RoomAttendant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignedRoomRepository extends CrudRepository<AssignedRoom, Integer> {
    List<AssignedRoom> findAllByRoomAttendant(RoomAttendant roomAttendant);
}
