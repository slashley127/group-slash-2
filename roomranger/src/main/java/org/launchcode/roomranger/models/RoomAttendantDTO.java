package org.launchcode.roomranger.models;


import java.util.List;

public class RoomAttendantDTO {

    private int id;

    private String firstName;

    private String lastName;

    private int managerId;

    private String managerFirstName;



    private String managerLastName;


    private int userId;

    private String username;


    private String password;
    private String pronoun;

    private String phoneNumber;



    private List workingDays ;

    private String email;
    private String notes;

    private  String role;



    private String workingDaysString;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getPronoun() {
        return pronoun;
    }

    public void setPronoun(String pronoun) {
        this.pronoun = pronoun;
    }

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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getManagerFirstName() {
        return managerFirstName;
    }

    public void setManagerFirstName(String managerFirstName) {
        this.managerFirstName = managerFirstName;
    }

    public String getManagerLastName() {
        return managerLastName;
    }

    public void setManagerLastName(String managerLastName) {
        this.managerLastName = managerLastName;
    }

    public List getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(List workingDays) {
        this.workingDays = workingDays;
    }

    public String getWorkingDaysString() {
        return workingDaysString;
    }

    public void setWorkingDaysString(String workingDaysString) {
        this.workingDaysString = workingDaysString;
    }

}
