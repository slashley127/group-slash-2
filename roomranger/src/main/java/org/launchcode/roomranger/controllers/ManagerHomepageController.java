package org.launchcode.roomranger.controllers;
import jakarta.validation.Valid;
import org.launchcode.roomranger.data.RoomAssignedRepository;
import org.launchcode.roomranger.data.RoomAttendantRepository;
import org.launchcode.roomranger.data.RoomRepository;
import org.launchcode.roomranger.models.Room;
import org.launchcode.roomranger.models.Task;
import org.launchcode.roomranger.models.RoomAssigned;
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
    private RoomAssignedRepository roomAssignedRepository;

    @GetMapping
    public String renderManagerHomepage(Model model) {
        Iterable<RoomAssigned> roomsAssigned = roomAssignedRepository.findAll();
        model.addAttribute("roomAssigned", roomsAssigned);
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
        model.addAttribute(new RoomAssigned());
        model.addAttribute("rooms", roomRepository.findAll());
        model.addAttribute("status", Status.values());
        model.addAttribute("task", Task.values());
        return "manager/assignroom";
    }

    @PostMapping("assignroom")
    public String processAssigningForm(@ModelAttribute("roomAssigned") @Valid RoomAssigned RoomAssigned, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("rooms", roomRepository.findAll());
            model.addAttribute("status", Status.values());
            model.addAttribute("task", Task.values());
            return "manager/assignroom";
        }
        roomAssignedRepository.save(RoomAssigned);
        return "redirect:/manager";
    }
}
