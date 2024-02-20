package org.launchcode.roomranger.data;

import jakarta.validation.constraints.NotNull;
import org.launchcode.roomranger.models.Manager;
import org.launchcode.roomranger.models.RoomAttendant;
import org.launchcode.roomranger.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomAttendantRepository extends CrudRepository<RoomAttendant, Integer> {
    @NotNull
    List<RoomAttendant> findAll();
    List<RoomAttendant> findAllByManager(Manager manager);
    RoomAttendant findById(int id);

    RoomAttendant findByUser(User user);
}
