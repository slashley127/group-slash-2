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
//    @ManyToOne
//    private RoomAttendant roomAttendantAssigned;
//    @ManyToOne
//    private Manager managerCreator;
//
//    private Occupancy roomOccupancy;

    private boolean available;
//    private CleaningTask cleaningTask;
//
//    private Status status;
//
//    private String note;
//    private String guest;
//    private int numberOfGuests;
//    private LocalDate checkinDate;
//    private LocalDate checkoutDate;
//    @OneToMany(mappedBy = "room")
//    private List<Comment> comments = new ArrayList<>();
    public Room() {
    }

//    public Room(String roomNumber, Type roomType, RoomAttendant roomAttendantAssigned, Manager managerCreator, boolean available, CleaningTask cleaningTask, Status status, String note, String guest, int numberOfGuests, LocalDate checkinDate, LocalDate checkoutDate, List<Comment> comments) {
//        this.roomNumber = roomNumber;
//        this.roomType = roomType;
//        this.roomAttendantAssigned = roomAttendantAssigned;
//        this.managerCreator = managerCreator;
//        this.available = available;
//        this.cleaningTask = cleaningTask;
//
//        this.status = status;
//
//        this.note = note;
//        this.guest = guest;
//        this.numberOfGuests = numberOfGuests;
//        this.checkinDate = checkinDate;
//        this.checkoutDate = checkoutDate;
//        this.comments = comments;
//    }


    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Room(String roomNumber, Type roomType, boolean available) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.available = available;
    }

//    public AssignedRoom getAssignedRoom() {
//        return assignedRoom;
//    }
//
//    public void setAssignedRoom(AssignedRoom assignedRoom) {
//        this.assignedRoom = assignedRoom;
//    }

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

//    public CleaningTask getCleaningTask() {
//        return cleaningTask;
//    }
//
//    public void setCleaningTask(CleaningTask cleaningTask) {
//        this.cleaningTask = cleaningTask;
//    }
//
//
//    public Status getStatus() {
//        return status;
//    }
//
//    public void setStatus(Status status) {
//        this.status = status;
//    }
//
//    public String getNote() {
//        return note;
//    }
//
//    public void setNote(String note) {
//        this.note = note;
//    }
//
//    public String getGuest() {
//        return guest;
//    }
//
//    public void setGuest(String guest) {
//        this.guest = guest;
//    }
//
//    public int getNumberOfGuests() {
//        return numberOfGuests;
//    }
//
//    public void setNumberOfGuests(int numberOfGuests) {
//        this.numberOfGuests = numberOfGuests;
//    }
//
//    public LocalDate getCheckinDate() {
//        return checkinDate;
//    }
//
//    public void setCheckinDate(LocalDate checkinDate) {
//        this.checkinDate = checkinDate;
//    }
//
//    public LocalDate getCheckoutDate() {
//        return checkoutDate;
//    }
//
//    public void setCheckoutDate(LocalDate checkoutDate) {
//        this.checkoutDate = checkoutDate;
//    }
//
//    public List<Comment> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<Comment> comments) {
//        this.comments = comments;
//    }

    @Override
    public String toString() {
        return roomNumber;
    }

}
