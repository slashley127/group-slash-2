package org.launchcode.roomranger.controllers;

import org.launchcode.roomranger.models.Author;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
@Controller
public class AboutUsController {

    //@RequestMapping(manager);
    @GetMapping("aboutUs")
    public String displayAboutUs(Model model) {
        List<Author> authors = new ArrayList<>();

        authors.add(new Author(
                "Samata Karka ",
                "/images/creators/pic-of-darren.jpeg",
                "Backend, User Role Configuration, Creating Crew Members and Chores" ,
                "Music theorist turned developer. Links below to say hello!",
                "https://www.linkedin.com/in/darrenlacour/",
                "https://github.com/lacourd"
        ));

        authors.add(new Author(
                "Francesca",
                "/images/creators/pic-of-Marcie.jpeg",
                "Front-End Development, Reward MVC, User Authentication",
                "Client-Facing Project Manager and Developer",
                "https://www.linkedin.com/in/marcie-defonce/",
                "https://github.com/mdefonce"
        ));
        authors.add(new Author(
                "Luna Liu",
                "/images/creators/pic-of-audra.jpeg",
                "User Authorization, About the Creators, Rewards Page",
                "Former teacher turned coder. Check my links to learn more.",
                "https://www.linkedin.com/in/audra-hartwell-252a66113/",
                "https://github.com/AudraHartwell"
        ));


        authors.add(new Author(
                "Sonal",
                "/images/creators/pic-of-Mike.jpeg",
                "Backend, Creating Chore Details Page",
                "Current cook and coder.",
                "https://www.linkedin.com/in/michael-zanger-ba8b1a287/",
                "https://github.com/mikezanger"
        ));


        model.addAttribute("authors", authors);
        return "about_us";
    }
}