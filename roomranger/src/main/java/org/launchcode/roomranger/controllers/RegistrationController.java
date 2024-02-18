package org.launchcode.roomranger.controllers;

import jakarta.validation.Valid;
import org.launchcode.roomranger.data.UserRepository;
import org.launchcode.roomranger.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("http://localhost:3000")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    ResponseEntity<String> newUser(@Valid @RequestBody User newUser, BindingResult bindingResult) {
        // Check for validation errors
        if (bindingResult.hasErrors()) {
            // Get the first validation error and return an appropriate response
            FieldError error = bindingResult.getFieldErrors().get(0);
            return new ResponseEntity<>(error.getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        // Check for unique username
        if (userRepository.existsByUsername(newUser.getUsername())) {
            return new ResponseEntity<>("Username is already taken. Please choose a different one.", HttpStatus.BAD_REQUEST);
        }

        // Check for password complexity
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,30}$";
        if (!newUser.getPassword().matches(passwordRegex)) {
            return new ResponseEntity<>("Password must contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, 1 special character, and be between 8 and 30 characters long.", HttpStatus.BAD_REQUEST);
        }


        // Check if confirmPassword matches password
//        if (!newUser.getPassword().equals(newUser.getConfirmPassword())) {
//            return new ResponseEntity<>("Password and confirm password do not match.", HttpStatus.BAD_REQUEST);
//        }

        // Save the new user if all checks pass
        userRepository.save(newUser);
        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }

    @GetMapping("/users")
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/exists/{username}")
    public ResponseEntity<Boolean> checkUsernameExists(@PathVariable String username) {
        boolean exists = userRepository.existsByUsername(username);
        return ResponseEntity.ok(exists);
    }
}