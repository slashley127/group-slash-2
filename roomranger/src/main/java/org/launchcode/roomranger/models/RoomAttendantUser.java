package org.launchcode.roomranger.models;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import org.launchcode.roomranger.models.User;



    @Entity
    public class RoomAttendantUser extends User{

        @OneToOne(mappedBy = "roomAttendantUser")
        private RoomAttendant roomAttendant;

        public RoomAttendantUser(String username, String password) {
            super(username, password);
        }

        public RoomAttendant getRoomAttendant() {

            return roomAttendant;
        }

        public void setRoomAttendant(RoomAttendant roomAttendant) {
            this.roomAttendant = roomAttendant;
        }
    }

