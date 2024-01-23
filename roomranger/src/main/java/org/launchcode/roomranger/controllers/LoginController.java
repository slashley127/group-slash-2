package org.launchcode.roomranger.Controllers;

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


import java.util.Optional;

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

        // Check credentials directly using UserRepository
        User user = userRepository.findByUsername(loginForm.getUsername());

        if (user != null && user.getPassword().equals(loginForm.getPassword())) {
            // If login is successful, redirect to the user page
            return "redirect:/user-page";
        }

        // If credentials are not valid, add an error message
        bindingResult.rejectValue("password", "error.loginForm", "Invalid username or password");
        return "login";
    }


    @GetMapping("/user-page")
    public String showUserPage() {
        // Display user page after successful authentication
        return "user";
    }
}
