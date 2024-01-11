package org.launchcode.roomranger.controllers;

import org.launchcode.roomranger.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.launchcode.roomranger.data.UserRepository;


@Controller
@RequestMapping("/registration")
public class RegistrationController {
     @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("userData", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(User userData, Model model) {
        // Check if registration is successful
        if (performRegistration(userData, model)) {
            model.addAttribute("message", "Registration successful!");
        } else {
            model.addAttribute("message", "Registration failed. Please check your details and try again.");
        }
        model.addAttribute("userData", userData);

        return "registration";
    }


    private boolean performRegistration(User userData, Model model) {
        // Check if any field is left empty
        if (isAnyFieldEmpty(userData)) {
            model.addAttribute("message", "Please fill in all details.");
            return false;
        }

        // Check if the username is unique
        if (!isUsernameUnique(userData.getUsername())) {
            model.addAttribute("message", "Username already in use. Please choose another username.");
            return false;
        }

        // Validate the password
        if (!isPasswordValid(userData.getPassword())) {
            model.addAttribute("message", "Password does not meet security requirements.");
            return false;
        }
        // Store user information in the database
        storeUserInformation(userData);

        return true;
    }


    private boolean isAnyFieldEmpty(User userData) {
        // Check if any field is left empty
        return userData.getFirstName().isEmpty() ||
                userData.getLastName().isEmpty() ||
                userData.getDob().isEmpty() ||
                userData.getUsername().isEmpty() ||
                userData.getPassword().isEmpty() ||
                userData.getConfirmPassword().isEmpty();
    }

    private boolean isUsernameUnique(String username) {
       //Check if the username is unique in the database
        return userRepository.findByUsername(username) == null;

    }

    private boolean isPasswordValid(String password) {
        // Validate the password based on security requirements
        // At least one capital, one small letter, one number (0-9), and one special character
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(passwordRegex);
    }

    private void storeUserInformation(User userData) {
        // Placeholder: Store user information in the database
        // Replace this with your actual database interaction code
        // You may use a service or repository to interact with the database
        // userService.save(userData);
    }
}
