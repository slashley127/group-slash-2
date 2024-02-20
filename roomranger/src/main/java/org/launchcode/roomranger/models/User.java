package org.launchcode.roomranger.models;

import jakarta.persistence.*;


@Entity
public class User extends AbstractEntity{




    @Column(nullable = false)
    @Basic(optional = false)
    private String username;
    @Column(nullable = false)
    @Basic(optional = false)
    private String password;
    @Column(nullable = false)
    @Basic(optional = false)
    private String role;


//    private String firstName;
//
//
//    private String lastName;
//
//    private String email;





    // Getters and setters...



//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//


//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
