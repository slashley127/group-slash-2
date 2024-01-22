package org.launchcode.roomranger.controllers;

import org.launchcode.roomranger.data.RoomAttendantRepository;
import org.launchcode.roomranger.data.RoomRepository;
import org.launchcode.roomranger.models.Room;
import org.launchcode.roomranger.models.RoomAttendant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomAttendantRepository roomAttendantRepository;
    @GetMapping("dashboard")
    public String renderDashboard(Model model){
        List<Room> rooms;
//        rooms = roomRepository.findAllByAttendantAssigned();
        model.addAttribute("title","Dashboard");
        return "attendant/dashboard";
    }
}
