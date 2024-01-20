package org.launchcode.roomranger.models;

public enum RoomStatus {

        WORKING("In Progress"),
        DIRTY("Dirty"),
        CLEANED("Cleaned"),
        INSPECTED("Inspected");

        private final String displayName;

        RoomStatus(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

