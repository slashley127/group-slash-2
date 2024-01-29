package org.launchcode.roomranger.controllers;

import jakarta.validation.Valid;
import org.launchcode.roomranger.data.ManagerRepository;
import org.launchcode.roomranger.data.RoomAttendantRepository;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.launchcode.roomranger.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.launchcode.roomranger.models.RoomAttendant;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "roomAttendant")
public class RoomAttendantController {

    @Autowired
    private RoomAttendantRepository roomAttendantRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ManagerRepository managerRepository;

    @GetMapping
    public String displayAllRoomAttendants(Model model) {
        model.addAttribute("title", "All Room Attendants");
        model.addAttribute("roomAttendant", roomAttendantRepository.findAll());
        //System.out.println("Room attendant list size:"+roomAttendantRepository.findAll().size());
        return "roomAttendant/index";
    }


    @GetMapping("add")
    public String addRoomAttendant(Model model) {
        model.addAttribute("title", "Add Room Attendant");
        model.addAttribute(new RoomAttendant());
        return "roomAttendant/add";
    }


    @PostMapping("add")
    public String processRoomAttendantForm(@ModelAttribute @Valid RoomAttendant newRoomAttendant, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            return "roomAttendant/add";
        } else {
            model.addAttribute("title", "Add a Room Attendant");
            redirectAttributes.addFlashAttribute("message", "The user has been saved successfully.");
            roomAttendantRepository.save(newRoomAttendant);
            System.out.println("Successfully saved entity");
            return "redirect:/roomAttendant";
        }


    }


    @GetMapping("update/{id}")
    public String  updateRoomAttendant(@PathVariable int id) {
        RoomAttendant roomAttendant = roomAttendantRepository.findById(id);
        return "roomAttendant/update";
    }

    @PostMapping ("update/{id}")
    public String updateForm(@ModelAttribute @Valid RoomAttendant roomAttendant, @PathVariable int id,Errors errors, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("title", "Update a Room Attendant");
        if (errors.hasErrors()) {
            return "roomAttendant/update";
        } else {
            redirectAttributes.addFlashAttribute("message", "The user has been saved successfully.");
            roomAttendantRepository.save(roomAttendant);
            System.out.println("Successfully saved entity");
            return "redirect:/roomAttendant";
        }
//        if (result.hasErrors()) {
//            roomAttendant.setId(id);
//            return "roomAttendant/update";
//        }
//        redirectAttributes.addFlashAttribute("message", "The user has been saved successfully.");
//
//        roomAttendantRepository.save(roomAttendant);
//        return "redirect:/roomAttendant";
    }

        @GetMapping("delete/{id}")
        public String processDelete ( @PathVariable int id, RedirectAttributes redirectAttributes){
            System.out.println("ID to be deletreturn \"redirect:/roomAttendant\";ed:" + id);
            RoomAttendant roomAttendant = roomAttendantRepository.findById(id);
                  // .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

            //System.out.println("Room attendant ID:" + roomAttendant.getId());
            roomAttendantRepository.delete(roomAttendant);

            redirectAttributes.addFlashAttribute("message", "The user has been deleted successfully.");


            return "redirect:/roomAttendant";
        }
    }

    













