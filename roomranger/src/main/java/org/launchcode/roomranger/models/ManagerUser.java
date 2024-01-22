package org.launchcode.roomranger.models;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class ManagerUser extends User {
    @OneToOne(mappedBy = "managerUser")
    private Manager manager;

    public ManagerUser(String username, String password) {
        super(username, password);
    }
    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

}

