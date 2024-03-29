package org.launchcode.roomranger.models;

public enum Type {
    STANDARD("Standard Room"),
    DOUBLE("Double Room"),
    SUITE("Suite"),
    DELUXE("Deluxe Room"),
    EXECUTIVE("Executive Room"),
    CONNECTING("Connecting Rooms"),
    JUNIOR("Junior Suite"),
    PRESIDENTIAL("Presidential Suite"),
    FAMILY("Family Room"),
    ACCESSIBLE("Accessible Room"),
    PENTHOUSE("Penthouse Suite");

    private final String displayName;

    Type(String displayName)
    {this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
}
