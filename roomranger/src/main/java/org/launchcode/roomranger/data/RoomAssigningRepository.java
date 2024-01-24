package org.launchcode.roomranger.data;

import org.launchcode.roomranger.models.RoomAssigning;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomAssigningRepository extends CrudRepository<RoomAssigning, Integer> {
}
