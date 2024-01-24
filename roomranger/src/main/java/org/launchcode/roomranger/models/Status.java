package org.launchcode.roomranger.models;

public enum Status {
    NOT_STARTED("Not Started"),
    IN_PROGRESS("In progress"),
    CLEANED("Cleaned"),
    SERVICE_REFUSED("Service Refused"),
    INSPECTED("Inspected");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
