package org.launchcode.roomranger.controllers;

import org.launchcode.roomranger.models.DTO.LoginFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.launchcode.roomranger.data.UserRepository;
import org.launchcode.roomranger.models.User;

import java.util.Set;
import jakarta.validation.Valid;


@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginForm", new LoginFormDTO());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("loginForm") @Valid LoginFormDTO loginForm,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        User user = userRepository.findByUsername(loginForm.getUsername());
        if (user != null && user.getPassword().equals(loginForm.getPassword())) {
            // Authentication successful
            Set<String> roles = user.getRoles();
            if (roles.contains("manager")) {
                return "redirect:/manager-page";
            } else if (roles.contains("room_attendant")) {
                return "redirect:/room-attendant-page";
            } else {
                return "redirect:/user-page";
            }
        } else {
            // Invalid username or password
            bindingResult.rejectValue("password", "error.loginForm", "Invalid username or password");
            return "login";
        }

    }

    @GetMapping("/manager-page")
    public String showManagerPage() {
        return "manager";
    }

    @GetMapping("/room-attendant-page")
    public String showRoomAttendantPage() {
        return "room_attendant";
    }
}
