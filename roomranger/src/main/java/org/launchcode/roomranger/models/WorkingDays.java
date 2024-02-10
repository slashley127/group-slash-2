package org.launchcode.roomranger.models;

public enum WorkingDays {

        SUNDAY("Sunday"),
        MONDAY("Monday"),
        TUESDAY("Tuesday"),
        WEDNESDAY("Wednesday"),
        THURSDAY("Thursday"),
        FRIDAY("Friday"),
        SATURDAY("Saturday");

        public String getDisplayName() {
                return displayName;
        }

        private final String displayName;

        WorkingDays(String displayName) {
            this.displayName = displayName;
        }
}
