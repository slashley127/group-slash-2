package org.launchcode.roomranger.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.launchcode.roomranger.data.RoomAttendantRepository;
import org.launchcode.roomranger.data.RoomRepository;
import org.launchcode.roomranger.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
@Controller
@RequestMapping("rooms")
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomAttendantRepository roomAttendantRepository;

    @GetMapping
    public String displayAllRooms(@RequestParam(required = false) Integer attendantId, Model model, HttpSession session){
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("title", "All Rooms");
        model.addAttribute("rooms", roomRepository.findAll());
        return "rooms/index";
    }


    @GetMapping("create")
    public String displayCreateRoomForm(Model model){
        model.addAttribute("title","New Room");
        model.addAttribute( "types", RoomType.values()); //display the room type list
        model.addAttribute("occupancy",Occupancy.values()); //display room occupancy list
        model.addAttribute("status", Status.values()); //display room status list
        model.addAttribute("tasks", CleaningTask.values());
        model.addAttribute(new Room());
        return "rooms/create";
    }

    @PostMapping("create")
    public String processCreateRoomForm(@ModelAttribute @Valid Room newRoom, Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("title", "Add New Room");
            return "rooms/create";
        }
        //need authentication logic here
        roomRepository.save(newRoom);
        return "redirect:/rooms";
    }
}
