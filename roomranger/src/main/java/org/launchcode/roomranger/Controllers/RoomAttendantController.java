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
@RequestMapping("RoomAttendants")
public class RoomAttendantController {

    @Autowired
    private RoomAttendantRepository roomAttendantRepository;

    @Autowired
    private UserRepository userRepository;

  @Autowired
  private ManagerRepository managerRepository;

    private static final String userSessionKey = "user";

    public ManagerUser getManagerFromSession(HttpSession session) {
        Integer ManagerId = (Integer) session.getAttribute(userSessionKey);
        if (ManagerId == null) {
            return null;
        }

        Optional<User> manager = userRepository.findById(ManagerId);

        if (manager.isEmpty()) {
            return null;
        }
        return(ManagerUser)manager.get();
    }

    @GetMapping("")
    public String displayAllRoomAttendants(Model model, HttpSession session) {
        model.addAttribute("title","All Room Attendants");
        model.addAttribute("Room Attendant", roomAttendantRepository.findAll());
        return "RoomAttendants/index";
    };

@GetMapping("add")
public String displayAddRoomAttendant(Model model){
    model.addAttribute("title","Add New Room Attendant");
    model.addAttribute(new AddRoomAttendantDTO());
    return "RoomAttendants_form";
}

    @PostMapping("add")
    public String processAddRoomAttendantForm(@ModelAttribute @Valid AddRoomAttendantDTO addRoomAttendantDTO, Errors errors, HttpServletRequest request, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a Room Attendant");
            return "RoomAttendants/add";
        }

        User existingUser = userRepository.findByUsername(AddRoomAttendantDTO.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A crew member with that username already exists");
            model.addAttribute("title", "Add a Room Attendant");
            return "RoomAttendants/add";
        }

       RoomAttendantUser newRoomAttendantUser = new RoomAttendantUser(AddRoomAttendantDTO.getUsername(), AddRoomAttendantDTO.getPassword());
       userRepository.save(newRoomAttendantUser);
       RoomAttendant newRoomAttendant = new RoomAttendant(AddRoomAttendantDTO.getUsername(), AddRoomAttendantDTO.getLastName(), newRoomAttendantUser);
        roomAttendantRepository.save(newRoomAttendant);
      return "redirect:";
    }

}


