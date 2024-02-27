package org.launchcode.roomranger.service;

import org.launchcode.roomranger.data.LeaveRequestRepository;
import org.launchcode.roomranger.data.RoomAttendantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveRequestService {
    @Autowired
    private LeaveRequestRepository leaveRepository;
    @Autowired
    private RoomAttendantRepository roomAttendantRepository;

//    public LeaveRequest createLeaveRequest(int roomAttendantId, LeaveRequest newLeave){
//        RoomAttendant roomAttendant = roomAttendantRepository.findById(roomAttendantId);
////                .orElseThrow(()-> new EntityNotFoundException("RoomAttendant not found with id: "))
//        newLeave.setRoomAttendant(roomAttendant);
//        newLeave.setStatus("Pending");
////        newLeave.setSubmittedDate(LocalDate.now());
//        // Check 1: If duration exceed current available leave duration
//        if (newLeave.getInitialDays() < newLeave.getDuration())
//            throw new RuntimeException("You do not have sufficient leave balance");
//        // Check 2: If there's leave applied during the same duration
//        List<LeaveRequest> userLeaveList = leaveRepository.findByRoomAttendantId(newLeave.getRoomAttendant().getId());
//        for (LeaveRequest userLeave: userLeaveList){
//            //give validation check for later
//        }
//       return leaveRepository.save(newLeave);
//    }

//    public List<LeaveRequest> findAll(){
////        List<Leave> leaveList = (List<Leave>) this.leaveRepository.findAll();
//        List<LeaveRequest> leaveList = leaveRepository.findAll();
//        return leaveList;
//    }

}
