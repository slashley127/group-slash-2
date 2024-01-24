package org.launchcode.roomranger.Controllers;

import org.launchcode.roomranger.Repository.RoomAttendantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("profile")
public class RoomAttendantProfileController {
    @Autowired
    private RoomAttendantRepository roomAttendantRepository;



}
