package org.launchcode.roomranger.models;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class RoomAssigning extends AbstractEntity {

    private String guest;
    private int numberOfGuests;
    private Status status;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private CleaningTask cleaningTask;
    private String note;

    public RoomAssigning() {}

    public RoomAssigning(String guest, int numberOfGuests, Status status, LocalDate checkInDate, LocalDate checkOutDate, CleaningTask cleaningTask, String note) {
        this.guest = guest;
        this.numberOfGuests = numberOfGuests;
        this.status = status;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.cleaningTask = cleaningTask;
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

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public CleaningTask getCleaningTask() {
        return cleaningTask;
    }

    public void setCleaningTask(CleaningTask cleaningTask) {
        this.cleaningTask = cleaningTask;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
