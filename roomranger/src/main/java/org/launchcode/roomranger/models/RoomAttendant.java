package org.launchcode.roomranger.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class RoomAttendant extends AbstractEntity{
    private String firstName;
    private String lastName;
    @ManyToOne
    private Manager manager;
    @OneToMany(mappedBy = "roomAttendantAssigned")
    private List<Room> roomsAssigned = new ArrayList<>();
    private String email;
    private int phoneNumber;
    private List<WorkingDays> workingDays = new ArrayList<>();


}
