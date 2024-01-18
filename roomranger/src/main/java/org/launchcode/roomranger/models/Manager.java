package org.launchcode.roomranger.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Manager extends AbstractEntity{
    private String firstName;
    private String lastName;
   @OneToMany(mappedBy = "manager")
    private List<RoomAttendant> roomAttendants = new ArrayList<>();

   @OneToMany(mappedBy = "managerCreator")
    private List<Room> rooms = new ArrayList<>();

    public Manager() {
    }

    public Manager(String firstName, String lastName, List<RoomAttendant> roomAttendants, List<Room> rooms) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roomAttendants = roomAttendants;
        this.rooms = rooms;
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

    public List<RoomAttendant> getRoomAttendants() {
        return roomAttendants;
    }

    public void setRoomAttendants(List<RoomAttendant> roomAttendants) {
        this.roomAttendants = roomAttendants;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
