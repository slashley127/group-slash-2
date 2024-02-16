package org.launchcode.roomranger.controllers;

import jakarta.validation.Valid;
import org.launchcode.roomranger.data.ManagerRepository;
import org.launchcode.roomranger.data.RoomAttendantRepository;
import org.launchcode.roomranger.exception.NotFoundException;
import org.launchcode.roomranger.exception.UserNotFoundException;
import org.launchcode.roomranger.service.RoomService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.launchcode.roomranger.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.launchcode.roomranger.models.RoomAttendant;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(value = "roomAttendant")
public class RoomAttendantController {
    @Autowired
    public RoomService roomService;
    @Autowired
    private RoomAttendantRepository roomAttendantRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ManagerRepository managerRepository;


    @GetMapping()
    public List<RoomAttendant> displayAllRoomAttendants() {
        return roomAttendantRepository.findAll();
    }

    @GetMapping("update/{id}")
    RoomAttendant getUserById(@PathVariable int id){
        return roomAttendantRepository.findById(id);
                //.orElseThrow(()->new NotFoundException("attendant with id " + id));
    }

     @PostMapping("/add")
      RoomAttendant addRoomAttendant( @RequestBody @Valid RoomAttendant newRoomAttendant) {
         System.out.println("Testing");

      return roomAttendantRepository.save(newRoomAttendant);
     }



    @PutMapping("/update/{id}")
    public RoomAttendant updateForm(@RequestBody RoomAttendant  newRoomAttendant, @PathVariable int id) {
        RoomAttendant updatedroomAttendant = roomAttendantRepository.findById(id);

        updatedroomAttendant.setFirstName(newRoomAttendant.getFirstName());
        updatedroomAttendant.setFirstName(newRoomAttendant.getLastName());
//        updatedroomAttendant.setPronoun(newRoomAttendant.getPronoun());
        updatedroomAttendant.setPhoneNumber(newRoomAttendant.getPhoneNumber());
        updatedroomAttendant.setEmail(newRoomAttendant.getEmail());
//        updatedroomAttendant.setWorkingDays(newRoomAttendant.getWorkingDays());
        updatedroomAttendant.setUsername(newRoomAttendant.getUsername());
        updatedroomAttendant.setPassword(newRoomAttendant.getPassword());
        updatedroomAttendant.setNotes(newRoomAttendant.getNotes());
        System.out.println("Successfully saved entity");

        //redirectAttributes.addFlashAttribute("message", "The user has been saved successfully.");
        return roomAttendantRepository.save(updatedroomAttendant);
                //.orElseThrow(()-> new UserNotFoundException(id));

}

    @GetMapping("profile/{id}")
    public RoomAttendant getProfile(@PathVariable int id) {
        return roomAttendantRepository.findById(id);
    }


        @GetMapping("/delete/{id}")
        public void deleteUser(@PathVariable int id) {
                    RoomAttendant roomAttendant = roomAttendantRepository.findById(id);
            if (!roomAttendantRepository.existsById(id)) {
                throw new UserNotFoundException(id);
            }
            roomAttendantRepository.delete(roomAttendant);
            //return "User with the id " + id + " has been successfully deleted.";
        }
    }


    













