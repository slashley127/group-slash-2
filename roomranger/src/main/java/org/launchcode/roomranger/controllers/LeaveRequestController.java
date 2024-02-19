package org.launchcode.roomranger.controllers;

import jakarta.validation.Valid;
import org.launchcode.roomranger.data.LeaveRequestRepository;
import org.launchcode.roomranger.data.RoomAttendantRepository;
import org.launchcode.roomranger.exception.NotFoundException;
import org.launchcode.roomranger.models.LeaveRequest;
import org.launchcode.roomranger.models.Room;
import org.launchcode.roomranger.models.RoomAttendant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping("leave")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;
    @Autowired
    private RoomAttendantRepository roomAttendantRepository;

    @GetMapping()
    public List<LeaveRequest> listLeaveRequests() {
        Sort sortByStatus = Sort.by(Sort.Order.desc("startDate"));
//        List<LeaveRequest> list = leaveRequestRepository.findAll(sortByStatus);
//        System.out.println(list);
        return leaveRequestRepository.findAll(sortByStatus);
    }

    @PostMapping("/add")
    public LeaveRequest submitLeaveRequestForm(@RequestBody LeaveRequest newLeave) {
        LocalDate startDate = newLeave.getStartDate();
        LocalDate endDate = newLeave.getEndDate();
        //calculate the duration
        int duration = (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;
        //exclude leave duration according to the working days
        int workingDayscount = 0;
//        RoomAttendant roomAttendant = newLeave.getRoomAttendant();
//        List<DayOfWeek> workingDays = parseWorkingDays(roomAttendant.getWorkingDays());
//        System.out.println(workingDays);
        newLeave.setDuration(duration);
        int remainingDays = newLeave.getRoomAttendant().getRemainingDays();
        if (remainingDays < newLeave.getDuration() || remainingDays <= 0)
            throw new RuntimeException("You do not have sufficient leave balance");
        if (newLeave.getStartDate().isAfter(newLeave.getEndDate())){
            throw new RuntimeException("Your End Date is After Start Date!");
        }
        newLeave.setStatus("Pending");
        newLeave.setSubmittedDate(LocalDate.now());
        return leaveRequestRepository.save(newLeave);
    }
    //convert String to Array of workingDays
    private List<DayOfWeek> parseWorkingDays(String workingDays) {
        return Arrays.stream(workingDays.split("\\s*,\\s*"))
                .map(day -> DayOfWeek.valueOf(day.toUpperCase()))
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<?> approveLeaveRequest(@PathVariable int id) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Leave Request with id " + id));
        if (leaveRequest.getStatus().equals("Approved") || leaveRequest.getStatus().equals("Rejected")) {
            throw new RuntimeException("The leave request has been processed!");
        }
        int remainingDays = leaveRequest.getRoomAttendant().getRemainingDays();
        if (remainingDays < leaveRequest.getDuration() || remainingDays <= 0) {
            throw new RuntimeException("You do not have sufficient leave balance");
        }
        leaveRequest.getRoomAttendant().setRemainingDays(remainingDays - leaveRequest.getDuration());
        leaveRequest.setStatus("Approved");
        leaveRequestRepository.save(leaveRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<?> rejectLeaveRequest(@PathVariable int id) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Leave Request with id " + id));
        if (leaveRequest.getStatus().equals("Approved") || leaveRequest.getStatus().equals("Rejected")) {
            throw new RuntimeException("The leave request has been processed!");
        }
        leaveRequest.setStatus("Rejected");
        leaveRequestRepository.save(leaveRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/edit")
    public LeaveRequest editLeaveRequest(@RequestBody @Valid LeaveRequest newLeaveRequest, @PathVariable int id) {
        return leaveRequestRepository.findById(id)
                .map(leaveRequest -> {
                    leaveRequest.setStartDate(newLeaveRequest.getStartDate());
                    leaveRequest.setEndDate(newLeaveRequest.getEndDate());
                    leaveRequest.setReason(newLeaveRequest.getReason());
                    return leaveRequestRepository.save(leaveRequest);
                }).orElseThrow(() -> new NotFoundException("Leave Request with id " + id));
    }
}
