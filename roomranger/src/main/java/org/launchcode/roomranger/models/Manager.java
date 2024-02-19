package org.launchcode.roomranger.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Manager extends AbstractEntity{
    @NotBlank(message="First name is required")
    @Column(length=30)
    private String firstName;
    @NotBlank(message="Last name is required")
    private String lastName;
    private String email;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;


    public Manager(String firstName, String lastName, String email, List<RoomAttendant> roomAttendants,User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

        this.user = user;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
