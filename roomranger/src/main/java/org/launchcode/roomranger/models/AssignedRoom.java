package org.launchcode.roomranger.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
public class AssignedRoom extends AbstractEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
//    @Valid
    private Room room;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "room_attendant_id")
////    @Valid
//    private RoomAttendant roomAttendant;

//    @Size(min = 2, max = 30, message = "Name of Guest must be 2-30 characters long")
    private String guest;

//    @Min(value = 1, message = "Number of Guests must be between 1-5")
//    @Max(value = 5, message = "Number of Guests must be between 1-5")
    private int numberOfGuests;

//    @NotNull(message = "Status is Required")
    private Status status;

//    @NotNull(message = "CheckIn Date is Required")
    private LocalDate checkIn;

//    @NotNull(message = "CheckOut Date is Required")
    private LocalDate checkOut;

//    @NotNull(message = "Task is Required")
    private Task task;
    private String note;

    public AssignedRoom() {
        this.room = room;
//        this.roomAttendant = roomAttendant;
        this.status = status;
        this.task = task;
    }

    public AssignedRoom(Room room, String guest, int numberOfGuests, Status status, LocalDate checkIn, LocalDate checkOut, Task task, String note) {
        this.room = room;
//        this.roomAttendant = roomAttendant;
        this.guest = guest;
        this.numberOfGuests = numberOfGuests;
        this.status = status;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.task = task;
        this.note = note;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
//
//    public RoomAttendant getRoomAttendant() {
//        return roomAttendant;
//    }
//
//    public void setRoomAttendant(RoomAttendant roomAttendant) {
//        this.roomAttendant = roomAttendant;
//    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
