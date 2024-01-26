package org.launchcode.roomranger.controllers;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.launchcode.roomranger.data.RoomAssigningRepository;
import org.launchcode.roomranger.data.RoomAttendantRepository;
import org.launchcode.roomranger.data.RoomRepository;
import org.launchcode.roomranger.models.Task;
import org.launchcode.roomranger.models.RoomAssigning;
import org.launchcode.roomranger.models.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("manager")
public class ManagerHomepageController {

    @Autowired
    private RoomAttendantRepository roomAttendantRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomAssigningRepository roomAssigningRepository;

    @GetMapping
    public String renderManagerHomepage(Model model) {
        Iterable<RoomAssigning> roomAssignings = roomAssigningRepository.findAll();
        model.addAttribute("roomAssigning", roomAssignings);
        return "manager/index";
    }

    @GetMapping("roomattendantlist")
    public String renderListOfRoomAttendants(Model model) {
        return "manager/roomattendantlist";
    }

    @GetMapping("roomslist")
    public String renderListOfRooms(Model model) {
        model.addAttribute("rooms", roomRepository.findAll());
        return "manager/roomslist";
    }


    @GetMapping("assignroom")
    public String renderAssignRoomForm(Model model) {
        model.addAttribute(new RoomAssigning());
        model.addAttribute("status", Status.values());
        model.addAttribute("task", Task.values());
        return "manager/assignroom";
    }

    @PostMapping("assignroom")
    public String processAssigningForm(@ModelAttribute("roomAssigning") @Valid RoomAssigning RoomAssigning, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("status", Status.values());
            model.addAttribute("task", Task.values());
            return "manager/assignroom";
        }
        roomAssigningRepository.save(RoomAssigning);
        return "redirect:/manager";
    }
}
