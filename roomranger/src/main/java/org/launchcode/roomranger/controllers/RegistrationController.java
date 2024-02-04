package org.launchcode.roomranger.controllers;

import org.launchcode.roomranger.data.UserRepository;
import org.launchcode.roomranger.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("http://localhost:3000")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

}