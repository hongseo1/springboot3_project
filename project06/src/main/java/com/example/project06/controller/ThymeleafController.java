package com.example.project06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {
    @GetMapping("index")
    public String showA(){
        return "index";
    }

/*  @GetMapping("login")
        public String showB(){
        return "login";
    }*/

/*    @GetMapping("board")
    public String showC(){
        return "board";
    }*/
    @GetMapping("qaboard")
    public String showD(){
        return "qaboard";
    }

    @GetMapping("popup.html")
    public String showE(){
        return "popup";
    }
}
