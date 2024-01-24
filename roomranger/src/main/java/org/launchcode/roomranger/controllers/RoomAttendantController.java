package org.launchcode.roomranger.controllers;

import jakarta.servlet.http.HttpSession;
import org.launchcode.roomranger.data.RoomAttendantRepository;
import org.launchcode.roomranger.data.RoomRepository;
import org.launchcode.roomranger.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("attendant")
public class RoomAttendantController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomAttendantRepository roomAttendantRepository;
    @GetMapping
    public String displayAllRooms(@RequestParam(required = false) Integer attendantId, Model model, HttpSession session){
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("title", "All Rooms");
        model.addAttribute("rooms", roomRepository.findAll());
        return "attendant/index";
    }

    //apply leave system
    @GetMapping("leave")
    public String displayLeaveForm(Model model){
        return "attendant/leaverequest";
    }
}
