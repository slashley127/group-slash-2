package org.launchcode.roomranger.controllers;

import org.launchcode.roomranger.models.Author;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AboutUsController {

    @GetMapping("/author")
    public List<Author> displayAboutUs() {
        List<Author> authors = new ArrayList<>();

        authors.add(new Author(
                "Samata Karka",
                "/images/",
                "In Progress",
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

        return authors;
    }
}
