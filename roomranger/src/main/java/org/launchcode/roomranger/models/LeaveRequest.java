package org.launchcode.roomranger.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class LeaveRequest extends AbstractEntity{
    private String firstName;
    private String lastName;
    @ManyToOne
    private RoomAttendant roomAttendant;
//    private final int initialDays = 20; //initial 10 days for every employee
    private int duration;
//    private int remainingDays = 20;
//    @NotBlank
    private LocalDate startDate;
//    @NotBlank
    private LocalDate endDate;
    private  LocalDate submittedDate ;
    private String status;

    private String reason;
// We have to know the which date is holiday, working days, and off days for attendants


    public LeaveRequest() {
    }

    public LeaveRequest(String firstName, String lastName, RoomAttendant roomAttendant, int duration, LocalDate startDate, LocalDate endDate, LocalDate submittedDate, String status, String reason) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roomAttendant = roomAttendant;
        this.duration = duration;
//        this.remainingDays = remainingDays;
        this.startDate = startDate;
        this.endDate = endDate;
        this.submittedDate = submittedDate;
        this.status = status;
        this.reason = reason;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public RoomAttendant getRoomAttendant() {
        return roomAttendant;
    }

    public void setRoomAttendant(RoomAttendant roomAttendant) {
        this.roomAttendant = roomAttendant;
    }

//    public int getInitialDays() {
//        return initialDays;
//    }

//    public int getRemainingDays() {
//        return remainingDays;
//    }
//
//    public void setRemainingDays(int remainingDays) {
//        this.remainingDays = remainingDays;
//    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getSubmittedDate() {
        return submittedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setSubmittedDate(LocalDate submittedDate) {
        this.submittedDate = submittedDate;
    }
}
