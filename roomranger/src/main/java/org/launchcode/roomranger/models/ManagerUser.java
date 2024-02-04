package org.launchcode.roomranger.models;

import jakarta.persistence.Entity;

@Entity
public class ManagerUser extends User{
 //@OneToOne(mappedBy = "managerUser")
   //private Manager manager;

    public ManagerUser() {
        super("defaultUsername", "defaultPassword");
    }

//    public ManagerUser(Manager manager) {
//        this.manager = manager;
//    }
//
//    public Manager getManager() {
//        return manager;
//    }
//
//    public void setManager(Manager manager) {
//        this.manager = manager;
//    }


}

