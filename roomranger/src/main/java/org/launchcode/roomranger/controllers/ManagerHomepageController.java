package org.launchcode.roomranger.controllers;

import jakarta.validation.Valid;
import org.launchcode.roomranger.data.AssignedRoomRepository;
import org.launchcode.roomranger.data.RoomAttendantRepository;
import org.launchcode.roomranger.data.RoomRepository;
import org.launchcode.roomranger.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

    @RestController
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @RequestMapping("assignedrooms")
    public class ManagerHomepageController {

        @Autowired
        private AssignedRoomRepository assignedRoomRepository;

        @Autowired
        private RoomRepository roomRepository;

        @Autowired
        private RoomAttendantRepository roomAttendantRepository;

        @GetMapping()
        public List<AssignedRoom> getAllAssignedRooms(){
            return (List<AssignedRoom>) assignedRoomRepository.findAll();
        }

//        @GetMapping("/{id}")
//        Optional<AssignedRoom> getAssignedRoomById(@PathVariable Integer id) {
//            return assignedRoomRepository.findById(id);
//        }
//
//        @PutMapping("/{id}")

    @GetMapping("/tasks")
    public Map<String, String> getTasks() {
        Map<String, String> tasks = new HashMap<>();
        for (Task task : Task.values()) {
            tasks.put(task.name(), task.getDisplayName());
        }
        return tasks;
    }
    @GetMapping("/tasks/{id}")
    public String getTask(@PathVariable int id) {
        return assignedRoomRepository.findById(id).get().getTask().getDisplayName();
    }

    @GetMapping("/statuses")
    public Map<String, String> getStatuses() {
        Map<String, String> statuses = new HashMap<>();
        for (Status status : Status.values()) {
            statuses.put(status.name(), status.getDisplayName());
        }
        return statuses;
    }
    @GetMapping("/statuses/{id}")
    public String getStatus(@PathVariable int id) {
        return assignedRoomRepository.findById(id).get().getStatus().getDisplayName();
    }



    @PostMapping(value = "/assignroomform")
    public ResponseEntity<AssignedRoom> assignRoom( @RequestBody AssignedRoom newAssignedRoom) {
        AssignedRoom savedRoom = assignedRoomRepository.save(newAssignedRoom);
        return new ResponseEntity<>(savedRoom, HttpStatus.OK);

        }


        }








//package org.launchcode.roomranger.controllers;
//import jakarta.servlet.http.HttpSession;
//import jakarta.validation.Valid;
//import org.launchcode.roomranger.data.AssignedRoomRepository;
//import org.launchcode.roomranger.data.RoomAttendantRepository;
//import org.launchcode.roomranger.data.RoomRepository;
//import org.launchcode.roomranger.models.Task;
//import org.launchcode.roomranger.models.AssignedRoom;
//import org.launchcode.roomranger.models.Status;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.*;
//
////@RestController
////@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
//@Controller
//@RequestMapping("manager")
//public class ManagerHomepageController {
//
//    @Autowired
//    private RoomAttendantRepository roomAttendantRepository;
//
//    @Autowired
//    private RoomRepository roomRepository;
//
//    @Autowired
//    private AssignedRoomRepository roomAssignedRepository;
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
//    public String processDeleteForm(@RequestParam(required = false) int[] assignedRoomId) {
//        for(int id : assignedRoomId) {
//            roomAssignedRepository.deleteById(id);
//        }
//        return "redirect:/manager";
//    }
//
//}
