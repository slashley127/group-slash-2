package org.launchcode.roomranger.controllers;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("manager")
public class ManagerHomepageController {


    @GetMapping("index")
    public String renderManagerHomepage(Model model){
        return "manager/index";
    }

    @GetMapping("roomattendantlist")
    public String renderListOfRoomAttendants(Model model){
        return "manager/roomattendantlist";
    }

}
