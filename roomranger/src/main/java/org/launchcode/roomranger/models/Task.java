package org.launchcode.roomranger.models;

public enum Task {
    CHECKOUT("Check-out Cleaning"),
    STAYOVER("Stay-over Cleaning"),
    //Conducted periodically to address areas that may not be cleaned during regular cleanings.
    DEEP("Deep Cleaning"),
    //A quick cleaning service requested by guests for specific areas or items without a full room cleaning.
    EXPRESS("Express Cleaning"),
    //For specific situations, such as addressing spills, stains, or addressing specific guest requests.
    SPECIALIZED("Specialized Cleaning"),
    //In response to the pandemic, many hotels have implemented enhanced cleaning protocols.
    COVID("COVID-19 Cleaning Protocols");
    private final String displayName;

    Task(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
