//package org.launchcode.roomranger.controllers;
//
//import org.launchcode.roomranger.data.ManagerRepository;
//import org.launchcode.roomranger.data.RoomAttendantRepository;
//import org.launchcode.roomranger.data.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("profile")
//public class AttendantprofileController {
//    @Autowired
//    private RoomAttendantRepository roomAttendantRepository;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private ManagerRepository managerRepository;
//
//    @GetMapping
//    public String viewProfile(Model model){
//        model.addAttribute("title","View Profile")
//        return "profile";
//    }
//
//}
