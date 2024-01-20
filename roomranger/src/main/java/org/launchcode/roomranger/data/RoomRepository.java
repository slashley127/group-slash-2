package org.launchcode.roomranger.data;

import org.launchcode.roomranger.models.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {
}
