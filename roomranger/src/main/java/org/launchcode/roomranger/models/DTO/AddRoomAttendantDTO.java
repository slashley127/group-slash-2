package org.launchcode.roomranger.models.DTO;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.launchcode.roomranger.models.WorkingDays;
import org.springframework.data.annotation.Id;

import java.util.List;


public class AddRoomAttendantDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @NotBlank(message = "First name is required")
    @Column(length = 30)
    private  String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;

    public void setWorkingDays(List<WorkingDays> workingDays) {
        this.workingDays = workingDays;
    }

    public List<WorkingDays> getWorkingDays() {
        return workingDays;
    }

    private List<WorkingDays> workingDays;
    private String gender;
    private String phoneNumber;
    private String notes;
    @Column(unique = true)
    private String email;
    @NotBlank
    @Size(min = 3, max = 20, message = "Invalid username. Must be between 3 and 30 characters.")
    private static String username;
    @NotBlank
    @Size(min = 5, max = 20, message = "Invalid password. Must be between 5 and 30 characters.")
    private static String password;


    public String getNote() {
        return notes;
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public  String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setNotes(String notes) {this.notes = notes;}

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getpassword() {
        return password;
    }

    public static String getusername() {
        return username;
    }
}
