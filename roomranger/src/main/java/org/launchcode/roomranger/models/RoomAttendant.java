package org.launchcode.roomranger.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

@Entity
public class RoomAttendant extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private int id;

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
    public String pronoun;
    private String workingDays;
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    private String notes;

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

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setId(int id) {this.id = id;}

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPronoun() {return pronoun;}

    public void setPronoun(String pronoun) {
        this.pronoun = pronoun;
    }
    public RoomAttendantUser getRoomAttendantUser() {return roomAttendantUser;}
    public String getWorkingDays() {return workingDays;}
    public void setWorkingDays(String workingDays) {this.workingDays = workingDays;}
    public String getusername() {return username;}
    public void setusername(String username) {this.username = username;}
    public void setpassword(String password) {this.password = password;}
    public Manager getManager() {return manager;}
    public void setManager(Manager manager) {this.manager = manager;}
    public RoomAttendantUser roomAttendantUser() {return roomAttendantUser();}
    public void setRoomAttendantUser(RoomAttendantUser roomAttendantUser) {
        this.roomAttendantUser = roomAttendantUser;
    }
    public RoomAttendant() {}
    public RoomAttendant(int id, String firstName, String lastName, String email, String phoneNumber, String username, String workingDays, String password, RoomAttendantUser roomAttendantUser, String notes, String pronoun) {
        // super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.manager = manager;
        this.workingDays = workingDays;
        this.username = username;
        this.password = password;
        this.roomAttendantUser = roomAttendantUser;
        this.notes = notes;
        this.pronoun = pronoun;
    }
}



   
