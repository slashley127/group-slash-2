package org.launchcode.roomranger.models;

public enum Status {

    NOT_STARTED("Not Started"),
    IN_PROGRESS("In Progress"),
    SERVICE_REFUSED("Service Refused"),
    READY("Ready"),
    INSPECTED("Inspected");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}