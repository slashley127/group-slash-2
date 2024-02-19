package org.launchcode.roomranger.data;

import org.launchcode.roomranger.models.LeaveRequest;
import org.launchcode.roomranger.models.RoomAttendant;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Integer> {
//    List<LeaveRequest> findAll();
    List<LeaveRequest> findByRoomAttendant(RoomAttendant roomAttendant);
    List<LeaveRequest> findByRoomAttendantId(int id);
    List<LeaveRequest> findAll(Sort sort);
}
