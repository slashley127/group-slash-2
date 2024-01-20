package org.launchcode.roomranger.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
//import org.apache.catalina.Manager;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class RoomAttendant extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable=false, updatable=false)
    private int ID;
    @NotBlank(message = "First name is required")
    @Column(length = 30)
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;

    //private final Object workingdays;

    //@Column(insertable=false, updatable=false
    //private String workingdays;
    private String Gender;
//    public enum Gender {
//        M("Male"),
//        F("Female"),
//        UNKNOWN("Unknown");
//
//    }

    private String PhoneNumber;


    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    @Column(unique = true)
    private String Email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(unique = true, length = 15)
    private char Username;
    @Column(unique = true, length = 15)
    private char Password;

    private List<WorkingDays> workingDays = new ArrayList<>();


    public char getUsername() {
        return Username;
    }

    public void setUsername(char username) {
        Username = username;
    }

    public char getPassword() {
        return Password;
    }

    public void setPassword(char password) {
        Password = password;
    }

    public Manager getManager() {

        return manager;
    }

    public void setManager(Manager manager) {

        this.manager = manager;
    }

    public RoomAttendantUser roomAttendantUser() {

        return roomAttendantUser();
    }



    @ManyToOne
    private Manager manager;

    @OneToOne(cascade = CascadeType.ALL)
    private RoomAttendantUser roomAttendantUser;



    public void setRoomAttendantUser(RoomAttendantUser roomAttendantUser) {
        this.roomAttendantUser = roomAttendantUser;
    }

    public RoomAttendant(String firstName, String lastName, RoomAttendantUser newRoomAttendantUser) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.manager = manager;
        this.roomAttendantUser = newRoomAttendantUser;
    }
}



