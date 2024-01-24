package org.launchcode.roomranger.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.launchcode.roomranger.data.CommentRepository;
import org.launchcode.roomranger.data.RoomAttendantRepository;
import org.launchcode.roomranger.data.RoomRepository;
import org.launchcode.roomranger.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("rooms")
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomAttendantRepository roomAttendantRepository;
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public String displayAllRooms(@RequestParam(required = false) Integer attendantId, Model model, HttpSession session){
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("title", "All Rooms");
        model.addAttribute("rooms", roomRepository.findAll());
        return "rooms/index";
    }


    @GetMapping("create")
    public String displayCreateRoomForm(Model model, HttpSession session){
        model.addAttribute("title","New Room");
//        model.addAttribute( "types", Type.values()); //display the room type list
//        model.addAttribute("occupancy",Occupancy.values()); //display room occupancy list
//        model.addAttribute("status", Status.values()); //display room status list
//        model.addAttribute("tasks", CleaningTask.values());
        model.addAttribute(new Room());
        return "rooms/create";
    }

    @PostMapping("create")
    public String processCreateRoomForm(@ModelAttribute @Valid Room newRoom, Errors errors, Model model, HttpSession session){
        if (errors.hasErrors()){
            model.addAttribute("title", "Add New Room");
            return "rooms/create";
        }
        //need authentication logic here
        roomRepository.save(newRoom);
        return "redirect:/rooms";
    }

    @PostMapping("comment")
    public String addComment(@RequestParam int roomId, @ModelAttribute @Valid Comment newComment){
        Optional<Room> result = roomRepository.findById(roomId);
        Room room = result.get();
        newComment.setRoom(room);
        newComment.setCreatedDate(LocalDate.now());
        commentRepository.save(newComment);
        return "redirect:/rooms";
    }
}
