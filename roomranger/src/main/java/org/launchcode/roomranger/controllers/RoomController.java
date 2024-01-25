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
        model.addAttribute( "types", Type.values()); //display the room type list
        model.addAttribute(new Room());
        return "rooms/create";
    }

    public void validateRoomNumber(String roomNumber){
        if (roomRepository.findByRoomNumber(roomNumber).isPresent()){
//            throw new RoomNumberNoticUniqueException("Room number is already in use");
            throw new RuntimeException("Room number is already in use");
        }
    }
    @PostMapping("create")
    public String processCreateRoomForm(@ModelAttribute @Valid Room newRoom, Errors errors, Model model, HttpSession session){
        if (errors.hasErrors()){
            model.addAttribute("title", "Add New Room");
            return "rooms/create";
        }
        validateRoomNumber(newRoom.getRoomNumber());
        //need authentication logic here
        roomRepository.save(newRoom);
        if (newRoom.isAvailable()){
            System.out.println("true");
        }
        return "redirect:/rooms";
    }

    @GetMapping("detail")
    public String displayRoomDetails(@RequestParam Integer roomId, Model model){
        Optional<Room> result = roomRepository.findById(roomId);
        if (result.isEmpty()){
            model.addAttribute("title", "Invalid Room: " + roomId);
        } else {
            Room room = result.get();
            model.addAttribute("title", "Room " + room.getRoomNumber() + " Details");
            model.addAttribute("room",room);
        }
        return "rooms/detail";
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
