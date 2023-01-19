package ru.sky.recipebook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class InfoController {

    @GetMapping
    String greeting() {
        return "Application is running";
    }

    @GetMapping("/info")
    String showInfo() {
        return "Author: Olga <br> " +
                "Project name: RecipeBook <br> " +
                "Was created at 21.01.2023 <br>" +
                "This project is dedicated to recipes description";
    }

}
