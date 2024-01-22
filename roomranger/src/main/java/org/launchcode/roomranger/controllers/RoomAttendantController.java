package org.launchcode.roomranger.Controllers;

import jakarta.servlet.http.HttpSession;
import org.launchcode.roomranger.data.RoomAttendantRepository;
import org.launchcode.roomranger.data.RoomRepository;
import org.launchcode.roomranger.data.UserRepository;
import org.launchcode.roomranger.models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("attendant")
public class RoomAttendantController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomAttendantRepository roomAttendantRepository;
    @GetMapping
    public String displayRoomsAssigned(Model model, HttpSession session){
        return "attendant/index";
    }
}
