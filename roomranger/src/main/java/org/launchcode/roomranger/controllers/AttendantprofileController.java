package org.launchcode.roomranger.controllers;

import org.launchcode.roomranger.data.ManagerRepository;
import org.launchcode.roomranger.data.RoomAttendantRepository;
import org.launchcode.roomranger.data.UserRepository;
import org.launchcode.roomranger.models.RoomAttendant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("roomAttendant")
public class AttendantprofileController {
    @Autowired
    private RoomAttendantRepository roomAttendantRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ManagerRepository managerRepository;

    @GetMapping("profile")
    public String getProfile(Model model) {
        model.addAttribute("title", "Add Room Attendant");
        model.addAttribute(new RoomAttendant());
        return "roomAttendant/profile";
    }

//    @PostMapping("/profile/{id}")
//    public String viewProfile(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes)
//    {
//        RoomAttendant roomAttendant = roomAttendantRepository.findById(id);
//        model.addAttribute("roomAttendant", roomAttendant);
//        redirectAttributes.addFlashAttribute("message", "The user has been saved successfully.");
//
//        return "profile";
//    }

}
