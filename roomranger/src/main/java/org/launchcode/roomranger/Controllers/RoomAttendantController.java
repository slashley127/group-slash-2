package org.launchcode.roomranger.Controllers;

        import jakarta.validation.Valid;
        import org.launchcode.roomranger.Repository.ManagerRepository;
        import org.launchcode.roomranger.Repository.RoomAttendantRepository;
        //import org.launchcode.roomranger.models.DTO.AddRoomAttendantDTO;
        import org.launchcode.roomranger.models.User;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.*;
        import org.launchcode.roomranger.Repository.UserRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.ui.Model;
        import org.springframework.validation.Errors;
        import org.launchcode.roomranger.models.RoomAttendant;
        import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
        }else{
            model.addAttribute("title", "Add a Room Attendant");
        redirectAttributes.addFlashAttribute("message", "The user has been saved successfully.");
        roomAttendantRepository.save(newRoomAttendant);
            System.out.println("Successfully saved entity");
            return "redirect:/roomAttendant";
             }


//        User existingUser = userRepository.findByUsername(AddRoomAttendantDTO.getusername());
//        if (existingUser != null) {
//            errors.rejectValue("username", "username.alreadyexists", "A Room Attendant with that username already exists");
//            model.addAttribute("title", "Add a Room Attendant");
//            return "roomAttendant";
//        }
//        RoomAttendant newRoomAttendant = new RoomAttendant(addRoomAttendantDTO.getID(), addRoomAttendantDTO.getFirstName(), AddRoomAttendantDTO.getLastName(), addRoomAttendantDTO.getEmail(), addRoomAttendantDTO.getPhoneNumber(), addRoomAttendantDTO.getWorkingDays(), addRoomAttendantDTO.getGender(), addRoomAttendantDTO.getNote());
//        RoomAttendant newRoomAttendant = new RoomAttendant();
//        roomAttendantRepository.save(newRoomAttendant);
        //return "redirect:";

    }
}





