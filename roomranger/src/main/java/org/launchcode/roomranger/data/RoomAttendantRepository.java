package org.launchcode.roomranger.data;

import org.launchcode.roomranger.data.RoomAttendantRepository;
import org.launchcode.roomranger.models.Manager;
import org.launchcode.roomranger.models.RoomAttendant;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RoomAttendantRepository extends CrudRepository<RoomAttendant, Integer> {

    List<RoomAttendant> findAll();
    List<RoomAttendant> findAllByManager(Manager manager);


}

