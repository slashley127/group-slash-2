package org.launchcode.roomranger.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class RoomAttendant extends AbstractEntity{
    private String firstName;
    private String lastName;
    @ManyToOne
    private Manager manager;
    @OneToOne(cascade = CascadeType.ALL)
    private RoomAttendant userAccount;
    @OneToMany(mappedBy = "roomAttendantAssigned")
    private List<Room> roomsAssigned = new ArrayList<>();
    private String email;
    private int phoneNumber;
    private List<WorkingDays> workingDays = new ArrayList<>();

    public RoomAttendant() {
    }

    public RoomAttendant(String firstName, String lastName, Manager manager, RoomAttendant userAccount, List<Room> roomsAssigned, String email, int phoneNumber, List<WorkingDays> workingDays) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.manager = manager;
        this.userAccount = userAccount;
        this.roomsAssigned = roomsAssigned;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.workingDays = workingDays;
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

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public RoomAttendant getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(RoomAttendant userAccount) {
        this.userAccount = userAccount;
    }

    public List<Room> getRoomsAssigned() {
        return roomsAssigned;
    }

    public void setRoomsAssigned(List<Room> roomsAssigned) {
        this.roomsAssigned = roomsAssigned;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<WorkingDays> getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(List<WorkingDays> workingDays) {
        this.workingDays = workingDays;
    }
}
