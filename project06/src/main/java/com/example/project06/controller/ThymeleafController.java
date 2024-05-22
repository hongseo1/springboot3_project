package com.example.project06.controller;

import com.example.project06.entity.Member;
import com.example.project06.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class ThymeleafController {
    @Autowired
    MemberRepository memberRepository;

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
}
