package org.launchcode.roomranger.controllers;

import org.launchcode.roomranger.models.DTO.RegistrationFormDTO;
import org.launchcode.roomranger.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.launchcode.roomranger.data.UserRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/Registration")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationFormDTO());
        return "Registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("registrationForm") RegistrationFormDTO registrationForm, Model model, RedirectAttributes redirectAttributes) {
        // Check if registration is successful
        if (performRegistration(registrationForm, model)) {
            redirectAttributes.addFlashAttribute("message", "Registration successful!");

            return "redirect:/";
        } else {
            return "Registration";
        }
    }

    private boolean performRegistration(RegistrationFormDTO registrationForm, Model model) {
        // Check if any field is left empty
        if (isAnyFieldEmpty(registrationForm)) {
            model.addAttribute("message", "Please fill in all details.");
            return false;
        }

        // Check if the username is unique
        if (!isUsernameUnique(registrationForm.getUsername())) {
            model.addAttribute("message", "Username already in use. Please choose another username.");
            return false;
        }

        // Validate the password
        if (!isPasswordValid(registrationForm.getPassword())) {
            model.addAttribute("message", "Password does not meet security requirements.");
            return false;
        }

        // Check if the password and confirmation password match
        if (!registrationForm.getPassword().equals(registrationForm.getConfirmPassword())) {
            model.addAttribute("message", "Password and confirmation password do not match.");
            return false;
        }

        // Convert RegistrationFormDTO to User
        User user = convertToUser(registrationForm);

        // Store user information in the database
        storeUserInformation(user);

        return true;
    }

    private boolean isUsernameUnique(String username) {
        // Check if the username is unique in the database
        User user = userRepository.findByUsername(username);

        if (user != null) {
            // Log that the username is not unique
            logger.info("Username {} is already in use.", username);
            return false;
        }

        // Log that the username is unique
        logger.info("Username {} is unique.", username);
        return true;
    }

    private User convertToUser(RegistrationFormDTO registrationForm) {
        User user = new User();
        user.setFirstName(registrationForm.getFirstName());
        user.setLastName(registrationForm.getLastName());
        user.setDob(registrationForm.getDob());
        user.setUsername(registrationForm.getUsername());
        user.setPassword(registrationForm.getPassword());

        return user;
    }

    private boolean isAnyFieldEmpty(RegistrationFormDTO registrationForm) {
        // Check if any field is left empty
        return registrationForm.getFirstName().isEmpty() ||
                registrationForm.getLastName().isEmpty() ||
                registrationForm.getDob().isEmpty() ||
                registrationForm.getUsername().isEmpty() ||
                registrationForm.getPassword().isEmpty() ||
                registrationForm.getConfirmPassword().isEmpty();
    }

    private boolean isPasswordValid(String password) {
        // Validate the password based on security requirements
        // At least one capital, one small letter, one number (0-9), and one special character
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(passwordRegex);
    }

    private void storeUserInformation(User user) {
        userRepository.save(user);
        logger.info("User {} registered successfully.", user.getUsername());

    }
}
