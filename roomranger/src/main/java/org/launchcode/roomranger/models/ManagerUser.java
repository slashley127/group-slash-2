package org.launchcode.roomranger.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class ManagerUser extends User{
    @OneToOne(mappedBy = "userAccount")
    private Manager manager;


}
