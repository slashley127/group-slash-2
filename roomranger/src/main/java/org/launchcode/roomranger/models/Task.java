package org.launchcode.roomranger.models;

public enum Task {
    STAY_OVER ("Stay Over"),
    STAY_OVER_FULL_LINEN("Stay Over Full Linen"),
    CHECK_OUT("Check Out"),
    TOUCH_UP("Touch Up");

    private final String displayName;

    Task(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


}
