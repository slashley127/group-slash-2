package org.launchcode.roomranger.models;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class RoomAssigning extends AbstractEntity {

    private String guest;
    private int numberOfGuests;
    private Status status;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Task task;
    private String note;

    public RoomAssigning() {}

    public RoomAssigning(String guest, int numberOfGuests, Status status, LocalDate checkIn, LocalDate checkOut, Task task, String note) {
        this.guest = guest;
        this.numberOfGuests = numberOfGuests;
        this.status = status;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.task = task;
        this.note = note;
    }

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
