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

    public Room() {
    }

    public Room(String roomNumber, RoomType roomType, RoomAttendant roomAttendantAssigned, Manager managerCreator, Occupancy roomOccupancy) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomAttendantAssigned = roomAttendantAssigned;
        this.managerCreator = managerCreator;
        this.roomOccupancy = roomOccupancy;
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

}


