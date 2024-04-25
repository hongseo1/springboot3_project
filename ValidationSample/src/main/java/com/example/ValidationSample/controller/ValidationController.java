package com.example.ValidationSample.controller;

import com.example.ValidationSample.form.CalcForm;
import com.example.ValidationSample.validator.CalcValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ValidationController {
    //form-backing bean
    @ModelAttribute
    //@ModelAttribute: CalcForm과 ValidationController를 연결
    public CalcForm setUpForm(){ //CalcForm클래스를 반환 타입으로 가지는 메소드
        return new CalcForm(); //초기화해서 돌려줌
    }

    @GetMapping("show")
    public String showView(){
        return "entry";
    }
    @PostMapping("calc")
    public String confirmView(
        @Validated CalcForm form, BindingResult bindingResult, Model model
        //{}안에서 사용할 변수 정의
    ){
        if(bindingResult.hasErrors()){//사용자가 잘못 입력 시
            return "entry"; //entry로 초기화
        }
        Integer result = form.getLeftNum() + form.getRightNum();
        model.addAttribute("result", result);

        return "confirm";
    }

    @Autowired
    CalcValidator calcValidator;
    @InitBinder("calcForm")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(calcValidator);
    }
}
