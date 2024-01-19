package org.launchcode.roomranger.models;

public enum Status {
    WORKING("In Progress"),
    DIRTY("Dirty"),
    CLEANED("Cleaned"),
    INSPECTED("Inspected");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }
}
