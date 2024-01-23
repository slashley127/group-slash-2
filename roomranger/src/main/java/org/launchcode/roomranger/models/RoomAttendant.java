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
    @Column(unique = true, length = 15)
    private String username;
    @Column(unique = true, length = 15)
    private String password;
    @ManyToOne
    private Manager manager;
    @OneToOne(cascade = CascadeType.ALL)
    private RoomAttendantUser roomAttendantUser;
    private String gender;
    private String phoneNumber;
    private List<WorkingDays> workingDays = new ArrayList<>();
    public String getGender() {return gender;}

    public void setGender(String gender) { this.gender = gender; }

    @Column(unique = true)
    private String email;

    public RoomAttendant() {

    }


    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWorkingDays(List<WorkingDays> workingDays) {
        this.workingDays = workingDays;
    }



    public RoomAttendant ( int ID, String firstName, String lastName, String email, String phoneNumber, List<WorkingDays> workingDays, String username, String password, RoomAttendantUser roomAttendantUser) {
        super();
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email= email;
        this.phoneNumber= phoneNumber;
        this.manager = manager;
        this.workingDays = workingDays;
        this.username= username;
        this.password=password;
        this.roomAttendantUser=roomAttendantUser;

    }

    public RoomAttendantUser getRoomAttendantUser() {
        return roomAttendantUser;
    }



    public String getusername() {return username;}

    public void setusername(String username) {this.username = username;}

    public void setpassword(String password) {this.password = password;}

    public Manager getManager() {return manager;}

    public void setManager(Manager manager) {this.manager = manager;}

    public RoomAttendantUser roomAttendantUser() {return roomAttendantUser();}

    public void setRoomAttendantUser(RoomAttendantUser roomAttendantUser) {
        this.roomAttendantUser = roomAttendantUser;
    }
}



