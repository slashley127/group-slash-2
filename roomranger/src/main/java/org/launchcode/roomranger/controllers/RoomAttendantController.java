package org.launchcode.roomranger.controllers;

import jakarta.validation.Valid;
import org.launchcode.roomranger.data.ManagerRepository;
import org.launchcode.roomranger.data.RoomAttendantRepository;
import org.launchcode.roomranger.exception.UserNotFoundException;
import org.launchcode.roomranger.models.*;
import org.launchcode.roomranger.models.Dto.RoomAttendantDTO;
import org.launchcode.roomranger.service.RoomService;
import org.springframework.web.bind.annotation.*;
import org.launchcode.roomranger.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

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
    public List<RoomAttendantDTO> displayAllRoomAttendants() {
        return getRoomAttendantDTOList(roomAttendantRepository.findAll());
    }



    @GetMapping("update/{id}")
    RoomAttendantDTO getUserById(@PathVariable int id){
        return getRoomAttendantDTO(roomAttendantRepository.findById(id));
//                .orElseThrow(()->new NotFoundException("attendant with id " + id));
    }


    @PostMapping("/add")
    RoomAttendantDTO addRoomAttendant( @RequestBody @Valid RoomAttendantDTO roomAttendantDTO) {
        RoomAttendant roomAttendantEntity = getRoomAttendantEntity(roomAttendantDTO);
        User userEntity = getUserEntity(roomAttendantDTO);
        userEntity.setRole("roomAttendant");
        userRepository.save(userEntity);
        roomAttendantEntity.setUser(userEntity);
        //TODO: Save Manager if needed
        roomAttendantRepository.save(roomAttendantEntity);
        roomAttendantDTO.setId(roomAttendantEntity.getId());
        roomAttendantDTO.setUserId(userEntity.getId());

        return roomAttendantDTO;
    }

    private User getUserEntity(RoomAttendantDTO roomAttendantDTO) {
        User userEntity = new User();
        userEntity.setPassword(roomAttendantDTO.getPassword());
        userEntity.setUsername(roomAttendantDTO.getUsername());
        return userEntity;
    }

    private RoomAttendant getRoomAttendantEntity(RoomAttendantDTO roomAttendantDTO) {
        RoomAttendant roomAttendantEntity = new RoomAttendant();
        roomAttendantEntity.setEmail(roomAttendantDTO.getEmail());
        roomAttendantEntity.setFirstName(roomAttendantDTO.getFirstName());
        roomAttendantEntity.setNotes(roomAttendantDTO.getNotes());
        roomAttendantEntity.setLastName(roomAttendantDTO.getLastName());
        roomAttendantEntity.setPhoneNumber(roomAttendantDTO.getPhoneNumber());
        roomAttendantEntity.setPronoun(roomAttendantDTO.getPronoun());
        roomAttendantEntity.setWorkingDays(getWorkingDaysStringFromList(roomAttendantDTO.getWorkingDays()));
        return roomAttendantEntity;
    }


    @PutMapping("/update/{id}")
    public RoomAttendantDTO updateForm(@RequestBody RoomAttendantDTO  roomAttendantDTO, @PathVariable int id) {
        RoomAttendant updatedroomAttendant = roomAttendantRepository.findById(id);

        populateRoomAttendantEntity(updatedroomAttendant,roomAttendantDTO);

        roomAttendantRepository.save(updatedroomAttendant);
        System.out.println("Successfully saved entity");


        return roomAttendantDTO;


    }


    @GetMapping("profile/{id}")
    public RoomAttendantDTO getProfile(@PathVariable int id) {
        return getRoomAttendantDTO(roomAttendantRepository.findById(id));
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


    @GetMapping("/workingDays")
    public Map<String, String> getDays() {
        Map<String, String> days = new HashMap<>();
        for (WorkingDays workingDays : WorkingDays.values()) {
            days.put(workingDays.name(), workingDays.getDisplayName());
        }
        return days;
    }

    private List<RoomAttendantDTO> getRoomAttendantDTOList(List<RoomAttendant> roomAttendantEntityList) {
        List<RoomAttendantDTO> roomAttendantDTOList = new ArrayList<RoomAttendantDTO>();
        for (RoomAttendant roomAttendantEntity: roomAttendantEntityList){
            RoomAttendantDTO roomAttendantDTO =  getRoomAttendantDTO(roomAttendantEntity);
            roomAttendantDTOList.add(roomAttendantDTO);

        }
        return roomAttendantDTOList;
    }
    private RoomAttendantDTO getRoomAttendantDTO(RoomAttendant roomAttendantEntity) {
        RoomAttendantDTO roomAttendantDTO = new RoomAttendantDTO();
        roomAttendantDTO.setId(roomAttendantEntity.getId());
        roomAttendantDTO.setEmail(roomAttendantEntity.getEmail());
        roomAttendantDTO.setFirstName(roomAttendantEntity.getFirstName());
        roomAttendantDTO.setNotes(roomAttendantEntity.getNotes());
        roomAttendantDTO.setLastName(roomAttendantEntity.getLastName());
        if(roomAttendantEntity.getUser()!=null){
            roomAttendantDTO.setPassword(roomAttendantEntity.getUser().getPassword());
            roomAttendantDTO.setUsername(roomAttendantEntity.getUser().getUsername());
            roomAttendantDTO.setUserId(roomAttendantEntity.getUser().getId());
            roomAttendantDTO.setRole(roomAttendantEntity.getUser().getRole());
        }
        roomAttendantDTO.setPhoneNumber(roomAttendantEntity.getPhoneNumber());
        roomAttendantDTO.setPronoun(roomAttendantEntity.getPronoun());
        roomAttendantDTO.setWorkingDays(getWorkingDaysListFromString(roomAttendantEntity.getWorkingDays()));
        if(roomAttendantEntity.getManager()!=null){
            roomAttendantDTO.setManagerFirstName(roomAttendantEntity.getManager().getFirstName());
            roomAttendantDTO.setManagerLastName(roomAttendantEntity.getManager().getLastName());
            roomAttendantDTO.setManagerId(roomAttendantEntity.getManager().getId());
        }


        return roomAttendantDTO;
    }

    private void populateRoomAttendantEntity(RoomAttendant roomAttendantEntity, RoomAttendantDTO roomAttendantDTO) {


        roomAttendantEntity.setEmail(roomAttendantDTO.getEmail());
        roomAttendantEntity.setFirstName(roomAttendantDTO.getFirstName());
        roomAttendantEntity.setNotes(roomAttendantDTO.getNotes());
        roomAttendantEntity.setLastName(roomAttendantDTO.getLastName());
        roomAttendantEntity.setPhoneNumber(roomAttendantDTO.getPhoneNumber());
        roomAttendantEntity.setPronoun(roomAttendantDTO.getPronoun());
        String workingDays = getWorkingDaysStringFromList(roomAttendantDTO.getWorkingDays());
        roomAttendantEntity.setWorkingDays(workingDays);

    }

    private String getWorkingDaysStringFromList(List<String> workingDays){
        //workingDays is stored as comma separated string in database
        //workingDayList is sent as a list to UI
        //This method is used before saving workingDays to Database
        String workingDaysString = "";
        for (String workingDay:workingDays){
            workingDaysString = workingDaysString+workingDay +",";
        }
        return  workingDaysString;
    }

    private List<String> getWorkingDaysListFromString(String workingDaysString){
        //workingDays is stored as comma separated string in database
        //workingDayList is sent as a list to UI
        //This method is used before sending the response to UI
        List<String> workingDaysList = Arrays.asList(workingDaysString.split(","));
        return  workingDaysList;
    }

}


    













