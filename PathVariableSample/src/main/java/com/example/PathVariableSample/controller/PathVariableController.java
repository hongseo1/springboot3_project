package com.example.PathVariableSample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PathVariableController {
    @GetMapping("show")
    public String showView(){
        return "show";
    }
    @GetMapping("/function/{no}")
    public String selectFunction(@PathVariable Integer no){
        //@PathVariable은 URL경로에 변수를 넣어주는게 가능하게 함
        String view = null;
        switch (no){
            case 1:
                view = "pathvariable/function1";
                break;
            case 2:
                view = "pathvariable/function2";
                break;
            case 3:
                view = "pathvariable/function3";
                break;
        }
        return view;
    }
    @PostMapping(value = "send", params = "a")
    public String showAView(){
        //반환값으로 뷰 이름을 돌려줌
        return "submit/a";
    }
    @PostMapping(value = "send", params = "b")
    public String showBView(){
        return "submit/b";
    }
    @PostMapping(value = "send", params = "c")
    public String showCView(){
        return "submit/c";
    }
    @PostMapping(value = "show", params = "h")
    public String showHView(){
        return "show";
    }
}
