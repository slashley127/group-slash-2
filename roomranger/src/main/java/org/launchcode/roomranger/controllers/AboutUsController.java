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
                "/images/",
                "In Progress" ,
                "In Progress",
                "In Progress",
                "In Progress"
        ));

        authors.add(new Author(
                "Francesca",
                "/images/",
                "In Progress",
                "In Progress",
                "In Progress",
                "In Progress"
        ));
        authors.add(new Author(
                "Luna Liu",
                "/images/",
                "In Progress",
                "In Progress",
                "In Progress",
                "In Progress"
        ));


        authors.add(new Author(
                "Sonal",
                "/images/creators/pic-of-Mike.jpeg",
                "In Progress",
                "In Progress",
                "In Progress",
                "In Progress"
        ));


        model.addAttribute("authors", authors);
        return "about_us";
    }
}