package org.launchcode.roomranger.models;

public enum RoomType {
    STANDARD("Standard Room"),
    DOUBLE("Double Room"),
    SUIT("SUIT"),
    DELUXE("Deluxe Rom"),
    EXECUTIVE("Executive Room"),
    CONNECTING("Connecting Rooms"),
    ADJOINING("Adjoining Rooms"),
    JUNIOR("Junior Suite"),
    PRESIDENTIAL("Presidential Suite"),
    FAMILY("Family Room"),
    ACCESSIBLE("Accessible Room"),
    PENTHOUSE("Penthouse Suite");

    private final String displayName;

    RoomType(String displayName)
    {this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
