package org.launchcode.roomranger.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class ManagerUser extends User{
    @OneToOne(mappedBy = "managerUser")
    private static Manager manager;

    public ManagerUser() {
    }

    public ManagerUser(Manager manager) {
        this.manager = manager;
    }

    public static Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }


}

