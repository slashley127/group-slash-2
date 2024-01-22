package org.launchcode.roomranger.data;

import org.launchcode.roomranger.models.Room;
import org.launchcode.roomranger.models.RoomAttendant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {
    //find the rooms assigned by room attendant
//    List<Room> findAllByAttendantAssigned(RoomAttendant roomAttendant);
//    List<Room> findAll();
}
