package org.launchcode.roomranger.controllers;

import jakarta.validation.Valid;
import org.launchcode.roomranger.data.ManagerRepository;
import org.launchcode.roomranger.data.RoomAttendantRepository;
import org.launchcode.roomranger.exception.UserNotFoundException;
import org.launchcode.roomranger.models.*;
import org.launchcode.roomranger.models.Dto.RoomAttendantDTO;
import org.launchcode.roomranger.service.RoomService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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


    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping()
    public List<RoomAttendantDTO> displayAllRoomAttendants() {
        if(isAuthenticatedAndIsManager()){

            return getRoomAttendantDTOList(roomAttendantRepository.findAll());

        }
        else {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username ="";
            if(userDetails!=null){
                username= userDetails.getUsername();
            }
            throw new AccessDeniedException("User does not have access to this resource");
        }


    }



    @GetMapping("update/{id}")
    RoomAttendantDTO getUserById(@PathVariable int id){
        if(isAuthenticatedAndIsManager()){

            return getRoomAttendantDTO(roomAttendantRepository.findById(id));

        }
        else {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username ="";
            if(userDetails!=null){
                username= userDetails.getUsername();
            }
            throw new AccessDeniedException("User does not have access to this resource");
        }
    }


    @PostMapping("/add")
    RoomAttendantDTO addRoomAttendant( @RequestBody @Valid RoomAttendantDTO roomAttendantDTO) {
        if(isAuthenticatedAndIsManager()){

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
        else {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username ="";
            if(userDetails!=null){
                username= userDetails.getUsername();
            }
            throw new AccessDeniedException("User does not have access to this resource");
        }

    }


    @PutMapping("/update/{id}")
    public RoomAttendantDTO updateForm(@RequestBody RoomAttendantDTO  roomAttendantDTO, @PathVariable int id) {
        if(isAuthenticatedAndIsManager()){

            RoomAttendant updatedroomAttendant = roomAttendantRepository.findById(id);

            populateRoomAttendantEntity(updatedroomAttendant,roomAttendantDTO);

            roomAttendantRepository.save(updatedroomAttendant);
            System.out.println("Successfully saved entity");


            return roomAttendantDTO;

        }
        else {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username ="";
            if(userDetails!=null){
                username= userDetails.getUsername();
            }
            throw new AccessDeniedException("User does not have access to this resource");
        }



    }


    @GetMapping("profile/{id}")
    public RoomAttendantDTO getProfile(@PathVariable int id) {
        RoomAttendant roomAttendant = roomAttendantRepository.findById(id);
        if(isAuthenticatedAndIsManager()){
            return getRoomAttendantDTO(roomAttendantRepository.findById(id));

        }
        else {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username ="";
            if(userDetails!=null){
                username= userDetails.getUsername();
            }
            throw new AccessDeniedException("User does not have access to this resource");
        }

    }


    @GetMapping("/delete/{id}")
    public void deleteUser(@PathVariable int id) {
        if(isAuthenticatedAndIsManager()){
            RoomAttendant roomAttendant = roomAttendantRepository.findById(id);
            if (!roomAttendantRepository.existsById(id)) {
                throw new UserNotFoundException(id);
            }
            roomAttendantRepository.delete(roomAttendant);

        }
        else {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username ="";
            if(userDetails!=null){
                username= userDetails.getUsername();
            }
            throw new AccessDeniedException("User does not have access to this resource");
        }
        //return "User with the id " + id + " has been successfully deleted.";
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
        if(workingDaysString.length()>0){
            return workingDaysString.substring(0, workingDaysString.length() - 1);
        }
        else{
            return  workingDaysString;
        }

    }

    private User getUserEntity(RoomAttendantDTO roomAttendantDTO) {
        User userEntity = new User();
        String encodedPassword = passwordEncoder.encode(roomAttendantDTO.getPassword());
        userEntity.setPassword(encodedPassword);
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


    private List<String> getWorkingDaysListFromString(String workingDaysString){
        //workingDays is stored as comma separated string in database
        //workingDayList is sent as a list to UI
        //This method is used before sending the response to UI
        List<String> workingDaysList = Arrays.asList(workingDaysString.split(","));
        return  workingDaysList;
    }

    private boolean  isAuthenticatedAndIsManager() {

        if(SecurityContextHolder.getContext().getAuthentication()!=null&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String role =null;
            for(GrantedAuthority grantedAuthority:userDetails.getAuthorities()){
                //TODO: Only 1 role
                role = grantedAuthority.getAuthority();
            }
            if(role!=null && role.equalsIgnoreCase("manager")){
                return  true;
            }
            else {
                return  false;
            }


        }
        else{
            //TODO: Need to throw 403 http error code if role is other than manager
            return  false;
        }


    }

}















