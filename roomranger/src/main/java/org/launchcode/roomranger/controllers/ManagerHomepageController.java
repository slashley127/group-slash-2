package org.launchcode.roomranger.controllers;

import org.launchcode.roomranger.data.RoomAttendantRepository;
import org.launchcode.roomranger.data.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("manager")
public class ManagerHomepageController {

    @Autowired
    private RoomAttendantRepository roomAttendantRepository;

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public String renderManagerHomepage(Model model){
        model.addAttribute("rooms", roomRepository.findAll());
        return "manager/index";
    }

    @GetMapping("roomattendantlist")
    public String renderListOfRoomAttendants(Model model){
        return "manager/roomattendantlist";
    }

    @GetMapping("assignroom")
    public String renderAssignRoomForm(Model model) {
        return "manager/assignroom";
    }
}
