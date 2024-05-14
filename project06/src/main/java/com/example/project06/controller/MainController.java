package com.example.project06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/index")
    public String showA(){
        return "index";
    }

    @GetMapping("/qaboard")
    public String showB(){
        return "qaboard";
    }
}
