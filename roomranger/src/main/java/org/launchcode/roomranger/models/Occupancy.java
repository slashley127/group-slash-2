package org.launchcode.roomranger.models;

public enum Occupancy {
//    VACANT("Vacant"),
//    OCCUPIED("Occupied"),
//    OUTOFORDER("Out of Order/Maintenance"),
//    RESERVED("Reserved");
    AVIAILABLE("Available"),
    NOTAVAILABLE("Not Available");
    private final String displayName;

    Occupancy(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
