package org.launchcode.roomranger.data;

import org.launchcode.roomranger.models.Room;
import org.launchcode.roomranger.models.RoomAttendant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    //find the rooms assigned by room attendant
    Optional<Room> findByRoomNumber(String roomNumber);
    List<Room> findByRoomAvailable (boolean available);
//    Optional<Room> findByRoomAttendantAssigned(Integer attendantId);
}
