package com.example.SpringMVCModelSample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("hello")
public class HelloModelController {
    //@GetMapping("bbb")
    @GetMapping("model")
    public String helloView(Model model){//springframework model 사용(스프링에서 자동생성)
        model.addAttribute("msg", "타임리프!!!");
        model.addAttribute("name", "홍서원");
        //return "helloThymeleaf";
        return "useThymeleaf";
    }
}
