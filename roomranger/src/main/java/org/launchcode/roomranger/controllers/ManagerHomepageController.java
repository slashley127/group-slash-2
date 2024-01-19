package org.launchcode.roomranger.controllers;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ManagerHomepageController {

    @GetMapping
    public String home(){
        return "managerhomepage";
    }

}
