package org.launchcode.roomranger.models;

public enum Status {
    WORKING("Cleaning in Progress"),
    DIRTY("Dirty"),
    CLEANED("Cleaned/Ready"),
    INSPECTED("Inspected");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
