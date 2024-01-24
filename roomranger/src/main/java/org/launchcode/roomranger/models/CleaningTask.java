package org.launchcode.roomranger.models;

public enum CleaningTask {
    STAY_OVER ("Stay Over"),
    STAY_OVER_FULL_LINEN("Stay Over Full Linen"),
    CHECK_OUT("Check Out"),
    TOUCH_UP("Touch Up")
    ;

    CleaningTask(String displayName) {
        this.displayName = displayName;
    }

    private final String displayName;
}
