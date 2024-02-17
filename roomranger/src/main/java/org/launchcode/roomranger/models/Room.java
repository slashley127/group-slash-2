package org.launchcode.roomranger.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Room extends AbstractEntity{

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AssignedRoom assignedRoom;
    @Size(min = 4,max = 4, message = "Please give a 4 digits number for Room number!")
    @NotBlank(message = "Give a Room Number!")
    private String roomNumber;
    private Type roomType;
    private boolean available;
    public Room() {
    }

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Room(String roomNumber, Type roomType, boolean available) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.available = available;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Type getRoomType() {
        return roomType;
    }

    public void setRoomType(Type roomType) {
        this.roomType = roomType;
    }

//    public RoomAttendant getRoomAttendantAssigned() {
//        return roomAttendantAssigned;
//    }
//
//    public void setRoomAttendantAssigned(RoomAttendant roomAttendantAssigned) {
//        this.roomAttendantAssigned = roomAttendantAssigned;
//    }
//
//    public Manager getManagerCreator() {
//        return managerCreator;
//    }
//
//    public void setManagerCreator(Manager managerCreator) {
//        this.managerCreator = managerCreator;
//    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }


    @Override
    public String toString() {
        return roomNumber;
    }
}
