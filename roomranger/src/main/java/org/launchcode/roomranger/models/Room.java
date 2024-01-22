package org.launchcode.roomranger.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
public class Room extends AbstractEntity{

    @NotBlank
    private String roomNumber;
    private RoomType roomType;
    @ManyToOne
    private RoomAttendant roomAttendantAssigned;
    @ManyToOne
    private Manager managerCreator;
    private Occupancy roomOccupancy;
    private String cleaningTask;
    private RoomStatus roomStatus;
    private String note;
    private String guest;
    private int numberOfGuests;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;

    public Room() {
    }

    public Room(String roomNumber, RoomType roomType, RoomAttendant roomAttendantAssigned, Manager managerCreator, Occupancy roomOccupancy, String cleaningTask, RoomStatus roomStatus, String note, String guest, int numberOfGuests, LocalDate checkinDate, LocalDate checkoutDate) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomAttendantAssigned = roomAttendantAssigned;
        this.managerCreator = managerCreator;
        this.roomOccupancy = roomOccupancy;
        this.cleaningTask = cleaningTask;
        this.roomStatus = roomStatus;
        this.note = note;
        this.guest = guest;
        this.numberOfGuests = numberOfGuests;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
    }

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public RoomAttendant getRoomAttendantAssigned() {
        return roomAttendantAssigned;
    }

    public void setRoomAttendantAssigned(RoomAttendant roomAttendantAssigned) {
        this.roomAttendantAssigned = roomAttendantAssigned;
    }

    public Manager getManagerCreator() {
        return managerCreator;
    }

    public void setManagerCreator(Manager managerCreator) {
        this.managerCreator = managerCreator;
    }

    public Occupancy getRoomOccupancy() {
        return roomOccupancy;
    }

    public void setRoomOccupancy(Occupancy roomOccupancy) {
        this.roomOccupancy = roomOccupancy;
    }

    public String getCleaningTask() {
        return cleaningTask;
    }

    public void setCleaningTask(String cleaningTask) {
        this.cleaningTask = cleaningTask;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
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

    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(LocalDate checkinDate) {
        this.checkinDate = checkinDate;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }
}
