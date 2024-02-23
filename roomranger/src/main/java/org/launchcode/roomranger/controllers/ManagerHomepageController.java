package org.launchcode.roomranger.controllers;

import jakarta.validation.Valid;
import org.launchcode.roomranger.data.AssignedRoomRepository;
import org.launchcode.roomranger.data.RoomRepository;
import org.launchcode.roomranger.exception.NotFoundException;
import org.launchcode.roomranger.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        public List<AssignedRoom> getAllAssignedRooms() {
            return (List<AssignedRoom>) assignedRoomRepository.findAll();
        }


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


//        @DeleteMapping("assignedroom/{id}")
//        public String deleteAssignedRoom(@PathVariable int id) {
//            if (!assignedRoomRepository.existsById(id)) {
//                throw new NotFoundException("Assignment with id " + id);
//            }
//            roomRepository.deleteById(id);
//            return "Assigned with Id " + id + "has been deleted successfully!";
//        }

        @GetMapping("assignedroom/{id}")
        public AssignedRoom getAssignedRoomById(@PathVariable int id) {
        return assignedRoomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("assigned room with id " + id));
        }

        @PutMapping("assignedroom/{id}")
        public AssignedRoom updateAssignedRoom(@RequestBody @Valid AssignedRoom newAssignedRoom, @PathVariable int id){
            return assignedRoomRepository.findById(id)
                    .map(assignedRoom -> {
                        assignedRoom.setRoomAttendant(roomAttendantRepository.findById(newAssignedRoom.getRoomAttendant().getId()));
                        assignedRoom.setRoom(newAssignedRoom.getRoom());
                        assignedRoom.setGuest(newAssignedRoom.getGuest());
                        assignedRoom.setNumberOfGuests(newAssignedRoom.getNumberOfGuests());
                        assignedRoom.setCheckIn(newAssignedRoom.getCheckIn());
                        assignedRoom.setCheckOut(newAssignedRoom.getCheckOut());
                        assignedRoom.setTask(newAssignedRoom.getTask());
                        assignedRoom.setNote(newAssignedRoom.getNote());
                        assignedRoom.setStatus(newAssignedRoom.getStatus());
                        return assignedRoomRepository.save(assignedRoom);
                    }).orElseThrow(()->new NotFoundException("Assigned Room with id " + id));
        }

        @PostMapping(value = "/create")
        public ResponseEntity<AssignedRoom> createAssignedRoom(@RequestBody @Valid AssignedRoom assignedRoom) {
            System.err.println("********************");
            RoomAttendant roomAttendant = roomAttendantRepository.findById(assignedRoom.getRoomAttendant().getId());
            AssignedRoom _assignedRoom = assignedRoomRepository.save(
                    new AssignedRoom(
                            assignedRoom.getRoom(),
                            roomAttendant,
                            assignedRoom.getGuest(),
                            assignedRoom.getNumberOfGuests(),
                            assignedRoom.getStatus(),
                            assignedRoom.getCheckIn(),
                            assignedRoom.getCheckOut(),
                            assignedRoom.getTask(),
                            assignedRoom.getNote())
            );
            System.err.println("Assigned Room: " + _assignedRoom.toString());
            Room _room = roomRepository.save(assignedRoom.getRoom());
            RoomAttendant _roomAttendant = roomAttendantRepository.save(roomAttendant);
            return new ResponseEntity<>(_assignedRoom, HttpStatus.CREATED);




        }



//        @PutMapping("assignedroom/{id}")
//        public AssignedRoom updateAssignedRoom(@RequestBody @Valid AssignedRoom newAssignedRoom, @PathVariable int id){
//            return assignedRoomRepository.findById(id)
//                    .map(assignedRoom -> {
//                        assignedRoom.setRoomAttendant(roomAttendantRepository.findById(newAssignedRoom.getRoomAttendant().getId()));
//                        assignedRoom.setRoom(newAssignedRoom.getRoom());
//                        assignedRoom.setGuest(newAssignedRoom.getGuest());
//                        assignedRoom.setNumberOfGuests(newAssignedRoom.getNumberOfGuests());
//                        assignedRoom.setCheckIn(newAssignedRoom.getCheckIn());
//                        assignedRoom.setCheckOut(newAssignedRoom.getCheckOut());
//                        assignedRoom.setTask(newAssignedRoom.getTask());
//                        assignedRoom.setNote(newAssignedRoom.getNote());
//                        assignedRoom.setStatus(newAssignedRoom.getStatus());
//                        return assignedRoomRepository.save(assignedRoom);
//                    }).orElseThrow(()->new NotFoundException("Assigned Room with id " + id));
//        }
//
//        @PostMapping(value = "/create")
//        public ResponseEntity<AssignedRoom> createAssignedRoom(@RequestBody @Valid AssignedRoom assignedRoom) {
//            System.err.println("********************");
//            RoomAttendant roomAttendant = roomAttendantRepository.findById(assignedRoom.getRoomAttendant().getId());
//            AssignedRoom _assignedRoom = assignedRoomRepository.save(
//                    new AssignedRoom(
//                            assignedRoom.getRoom(),
//                            roomAttendant,
//                            assignedRoom.getGuest(),
//                            assignedRoom.getNumberOfGuests(),
//                            assignedRoom.getStatus(),
//                            assignedRoom.getCheckIn(),
//                            assignedRoom.getCheckOut(),
//                            assignedRoom.getTask(),
//                            assignedRoom.getNote())
//            );
//            System.err.println("Assigned Room: " + _assignedRoom.toString());
//            Room _room = roomRepository.save(assignedRoom.getRoom());
//            RoomAttendant _roomAttendant = roomAttendantRepository.save(roomAttendant);
//            return new ResponseEntity<>(_assignedRoom, HttpStatus.CREATED);
//
//        }

    }
