package org.launchcode.roomranger.controllers;

import jakarta.validation.Valid;
import org.launchcode.roomranger.data.RoomAttendantRepository;
import org.launchcode.roomranger.data.RoomRepository;
import org.launchcode.roomranger.models.Occupancy;
import org.launchcode.roomranger.models.Room;
import org.launchcode.roomranger.models.RoomType;
import org.launchcode.roomranger.models.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("rooms")
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomAttendantRepository roomAttendantRepository;

    public RoomController() {
    }

    @GetMapping
    public String displayAllRooms(Model model){
        model.addAttribute("title", "All Rooms");
        model.addAttribute("rooms", roomRepository.findAll());
        return "rooms/index";
    }

    @GetMapping("create")
    public String displayCreateRoomForm(Model model){
        model.addAttribute("title","Add New Room");
        model.addAttribute( "types", RoomType.values()); //display the room type list
        model.addAttribute("occupancy",Occupancy.values()); //display room occupancy list
        model.addAttribute("status", Status.values()); //display room status list
        model.addAttribute(new Room());
        return "rooms/create";
    }

    @PostMapping("create")
    public String processCreateRoomForm(@ModelAttribute @Valid Room newRoom, Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("title", "Add New Room");
            return "rooms/create";
        }
        roomRepository.save(newRoom);
        return "redirect:/rooms";
    }
}
