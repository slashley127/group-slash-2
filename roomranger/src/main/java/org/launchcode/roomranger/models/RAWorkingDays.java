package org.launchcode.roomranger.models;

import jakarta.persistence.*;

import java.time.DayOfWeek;

@Entity
public class RAWorkingDays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private RoomAttendant roomAttendant;
    private DayOfWeek dayOfWeek;
    private boolean isWorkingDay;

    public RAWorkingDays(int id, RoomAttendant roomAttendant, DayOfWeek dayOfWeek, boolean isWorkingDay) {
        this.id = id;
        this.roomAttendant = roomAttendant;
        this.dayOfWeek = dayOfWeek;
        this.isWorkingDay = isWorkingDay;
    }

    public RAWorkingDays() {
    }

    public int getId() {
        return id;
    }

    public RoomAttendant getRoomAttendant() {
        return roomAttendant;
    }

    public void setRoomAttendant(RoomAttendant roomAttendant) {
        this.roomAttendant = roomAttendant;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public boolean isWorkingDay() {
        return isWorkingDay;
    }

    public void setWorkingDay(boolean workingDay) {
        isWorkingDay = workingDay;
    }
}
