package org.launchcode.roomranger.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;


import java.util.ArrayList;
import java.util.List;

@Entity
public class RoomAttendant extends AbstractEntity{




    @NotBlank(message = "First name is required")
    @Column(length = 30)
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;

    @ManyToOne
    private Manager manager;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    private String pronoun;
    @Size(max=10)
    private String phoneNumber;

    private String workingDays ;
    @Column(unique = true)
    private String email;
    private String notes;


    public void setWorkingDays(String workingDays) {
        this.workingDays = workingDays;
    }

    public String getWorkingDays() {
        return workingDays;
    }



    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
    public Manager getManager() {return manager;}

    public void setManager(Manager manager) {this.manager = manager;}



    public String getPronoun() {return pronoun;}

    public void setPronoun(String pronoun) {this.pronoun = pronoun;}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



}



   
