package org.launchcode.roomranger.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;


import java.util.ArrayList;
import java.util.List;

@Entity
public class RoomAttendant  extends AbstractEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable=false, updatable=false)
    private int id;

    @OneToMany(mappedBy = "roomAttendant")
    private List<AssignedRoom> assignedRoom = new ArrayList<>();

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
//    @OneToOne(cascade = CascadeType.ALL)
//    private RoomAttendantUser roomAttendantUser;
//    private String pronoun;
    @Size(max=10)
    private String phoneNumber;

//    public void setWorkingDays(String workingDays) {
//        this.workingDays = workingDays;
//    }
//
//    public String getWorkingDays() {
//        return workingDays;
//    }
//
//    private String workingDays ;
    @Column(unique = true)
    private String email;
    private String notes;

//    private String profilePic;

//    public String getProfilePic() {
//        return profilePic;
//    }

//    public void setProfilePic(String profilePic) {
//        this.profilePic = profilePic;
//    }
    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public void setId(int id) {
        this.id = id;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public void setEmail(String email) {
        this.email = email;
    }
//    public Manager getManager() {return manager;}
//
//    public void setManager(Manager manager) {this.manager = manager;}
//
//    public RoomAttendantUser getRoomAttendantUser() {return roomAttendantUser;}
//
//    public void setRoomAttendantUser(RoomAttendantUser roomAttendantUser) {this.roomAttendantUser = roomAttendantUser;}
//
//    public String getPronoun() {return pronoun;}
//
//    public void setPronoun(String pronoun) {this.pronoun = pronoun;}


    public RoomAttendant() {

    }
    public RoomAttendant ( String firstName, String lastName, String email, String phoneNumber, String username, String password, String notes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email= email;
        this.phoneNumber= phoneNumber;
//        this.workingDays = workingDays;
        this.username= username;
        this.password=password;
//        this.roomAttendantUser=roomAttendantUser;
        this.notes=notes;
    }


}



   
