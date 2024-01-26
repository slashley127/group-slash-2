//package org.launchcode.roomranger.controllers;
//
//import org.launchcode.roomranger.data.ManagerRepository;
//import org.launchcode.roomranger.data.RoomAttendantRepository;
//import org.launchcode.roomranger.data.UserRepository;
//import org.launchcode.roomranger.models.RoomAttendant;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.io.InputStream;
//
//@Controller
//public class RoomAttendantProfileController {
//    @Autowired
//    private RoomAttendantRepository roomAttendantRepository;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private RoomAttendant roomAttendant;
//
//    //    @ResponseBody
////    @GetMapping(value = "/images/image/{userName}", produces = MediaType.IMAGE_JPEG_VALUE)
////    public ResponseEntity<byte[]> retrieveImage(@PathVariable String userName) throws IOException {
////        InputStream inputStream = servletContext.getResourceAsStream(pathOfImageFile);
////        // This method can return inputStream
////        // freelancerService.retrieveImageFromFileSystem(userName);
////        return new ResponseEntity<byte[]>(IOUtils.toByteArray(ininputStream, HttpStatus.CREATED);
//    public String profile(Model model, RoomAttendant roomAttendant) {
//        model.addAttribute("title", "Profile");
//        model.addAttribute("profile", "profile");
//        //model.addAttribute("employee", roomAttendantRepository.findById());
//        return "profile";
//
//    }
//}
