package org.launchcode.roomranger.models;

import jakarta.persistence.Entity;

import java.time.LocalDate;


public class Leave extends AbstractEntity{
    private String firstName;
    private String lastName;
    private int employeeId;
    private final int initialDays = 20; //initial 10 days for every employee
    private int remainingDays;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate submittedDate;
// We have to know the which date is holiday, working days, and off days for attendants


}
