package ru.maxima.springtcsql.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartUpController {
    @GetMapping("/")
    public String start() {
        return "startpage";
    }
}
