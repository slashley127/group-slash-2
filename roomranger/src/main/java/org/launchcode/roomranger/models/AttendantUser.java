package org.launchcode.roomranger.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class AttendantUser extends User{
    @OneToOne(mappedBy = "attendantUser")
    private RoomAttendant roomAttendant;

    public AttendantUser() {}

    public AttendantUser(String username, String password, RoomAttendant roomAttendant) {
        super(username, password);
        this.roomAttendant = roomAttendant;
    }

    public RoomAttendant getRoomAttendant() {
        return roomAttendant;
    }

    public void setRoomAttendant(RoomAttendant roomAttendant) {this.roomAttendant = roomAttendant;}
}
