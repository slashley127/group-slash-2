package org.launchcode.roomranger.Controllers;

        import org.launchcode.roomranger.models.ManagerUser;
        import org.launchcode.roomranger.Repository.ManagerRepository;
        import org.launchcode.roomranger.Repository.RoomAttendantRepository;
        import org.launchcode.roomranger.models.DTO.AddRoomAttendantDTO;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.*;
        import org.launchcode.roomranger.Repository.UserRepository;
        import org.launchcode.roomranger.models.*;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.ui.Model;
        import org.springframework.validation.Errors;

        import jakarta.servlet.http.HttpServletRequest;
        import jakarta.servlet.http.HttpSession;
        import jakarta.validation.Valid;
        import java.util.Optional;

@Controller
@RequestMapping(value = "roomAttendants")
public class RoomAttendantController {

    @Autowired
    private RoomAttendantRepository roomAttendantRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ManagerRepository managerRepository;

    @GetMapping
    public String displayAllRoomAttendants(Model model) {
        model.addAttribute("title","All Room Attendants");
        model.addAttribute("Room Attendant", roomAttendantRepository.findAll());
        return "roomattendants/index";
    }
    @GetMapping(value = "/add")
    public String displayAddRoomAttendant(Model model){
    model.addAttribute("title","Add New Room Attendant");
    model.addAttribute(new AddRoomAttendantDTO());
    return "roomattendants/add";
    }
    @PostMapping(value = "/add")
    public String processRoomAttendantForm(@ModelAttribute @Valid AddRoomAttendantDTO addRoomAttendantDTO, Errors errors,  Model model) {
        //HttpServletRequest request,
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a Room Attendant");
            return "/roomattendants/add";
        }

        User existingUser = userRepository.findByUsername(AddRoomAttendantDTO.getUsername());
        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A Room Attendant with that username already exists");
            model.addAttribute("title", "Add a Room Attendant");
            return "/roomattendants/add";
        }

//       RoomAttendantUser newRoomAttendantUser = new RoomAttendantUser(AddRoomAttendantDTO.getUsername(), AddRoomAttendantDTO.getPassword());
//       userRepository.save(newRoomAttendantUser);
//       RoomAttendant newRoomAttendant = new RoomAttendant(AddRoomAttendantDTO.getUsername(), AddRoomAttendantDTO.getLastName(), newRoomAttendantUser);
//       roomAttendantRepository.save(newRoomAttendant);
       return "redirect:";
    }

}


