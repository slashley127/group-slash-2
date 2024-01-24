package org.launchcode.roomranger.Controllers;

import org.launchcode.roomranger.models.DTO.LoginFormDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller


public class HomeController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute(new LoginFormDTO());
        return "frontpage";
    }
}

