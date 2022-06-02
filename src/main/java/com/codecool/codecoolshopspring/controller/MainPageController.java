package com.codecool.codecoolshopspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MainPageController {

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }
}
