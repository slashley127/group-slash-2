package org.launchcode.roomranger.data;

import org.jetbrains.annotations.NotNull;
import org.launchcode.roomranger.models.Manager;
import org.launchcode.roomranger.models.RoomAttendant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
@Repository
public interface RoomAttendantRepository extends CrudRepository<RoomAttendant, Integer> {
    List<RoomAttendant> findAll();
    List<RoomAttendant> findAllByManager(Manager manager);
//    List<RoomAttendant>findbyId();
//
//    void update(RoomAttendant roomAttendant);
//    RoomAttendant getRoomAttendant(long id);
//    void deleteRoomAttendantbyId(long id);


}
