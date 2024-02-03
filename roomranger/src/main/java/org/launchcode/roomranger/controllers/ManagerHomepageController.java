package org.launchcode.roomranger.controllers;

import org.launchcode.roomranger.data.AssignedRoomRepository;
import org.launchcode.roomranger.data.RoomAttendantRepository;
import org.launchcode.roomranger.data.RoomRepository;
import org.launchcode.roomranger.models.AssignedRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class ManagerHomepageController {

    @RestController
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @RequestMapping("manager")
    public class ApiManagerHomepageController {

        @Autowired
        private AssignedRoomRepository assignedRoomRepository;

        @Autowired
        private RoomRepository roomRepository;

        @Autowired
        private RoomAttendantRepository roomAttendantRepository;



        @GetMapping()
        public Iterable<AssignedRoom> getAllAssignedRooms() {
            return assignedRoomRepository.findAll();
        }


        @PostMapping("/assignroom")
        public ResponseEntity<?> assignRoom(@RequestBody AssignedRoom newAssignedRoom){
            return new ResponseEntity<>(assignedRoomRepository.save(newAssignedRoom), HttpStatus.OK);
        }
    }












//
//
//
//    @GetMapping
//    public String renderManagerHomepage(Model model) {
//        Iterable<AssignedRoom> assignedRooms = roomAssignedRepository.findAll();
//        model.addAttribute("assignedRoom", assignedRooms);
//        return "manager/index";
//    }
//
//    @GetMapping("roomattendantlist")
//    public String renderListOfRoomAttendants(Model model) {
//        return "manager/roomattendantlist";
//    }
//
//    @GetMapping("roomslist")
//    public String renderListOfRooms(Model model) {
//        model.addAttribute("rooms", roomRepository.findAll());
//        return "manager/roomslist";
//    }
//
//
//    @GetMapping("assignroom")
//    public String renderAssignRoomForm(Model model) {
//        model.addAttribute(new AssignedRoom());
//        model.addAttribute("rooms", roomRepository.findAll());
//        model.addAttribute("status", Status.values());
//        model.addAttribute("task", Task.values());
//        return "manager/assignroom";
//    }
//
//    @PostMapping("assignroom")
//    public String processAssigningForm(@ModelAttribute("roomAssigned") @Valid AssignedRoom AssignedRoom, Errors errors, Model model) {
//        if (errors.hasErrors()) {
//            model.addAttribute("rooms", roomRepository.findAll());;
//            model.addAttribute("status", Status.values());
//            model.addAttribute("task", Task.values());
//            return "manager/assignroom";
//        }
//        roomAssignedRepository.save(AssignedRoom);
//        return "redirect:/manager";
//    }
//
//    @GetMapping("/delete")
//    public String displayDeleteForm(Model model, HttpSession session) {
//        model.addAttribute("assignedRoom", roomAssignedRepository.findAll());
//        return "manager/delete";
//    }
//
//    @PostMapping("/delete")
//        public String processDeleteForm(@RequestParam(required = false) int[] assignedRoomId) {
//        for(int id : assignedRoomId) {
//            roomAssignedRepository.deleteById(id);
//        }
//        return "redirect:/manager";
//        }
//
//}
//
//
//


}
