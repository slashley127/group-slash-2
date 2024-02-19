package org.launchcode.roomranger.models;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

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

    @OneToOne(mappedBy = "user")
    private Manager manager;

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