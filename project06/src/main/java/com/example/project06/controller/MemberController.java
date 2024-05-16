package com.example.project06.controller;

import com.example.project06.dto.MemberFormDto;
import com.example.project06.entity.Member;
import com.example.project06.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/join")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "join";
    }

    @PostMapping("/join")
    public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){ //에러가 있다면
            return "join"; //회원가입으로 돌아감
        }

        try { //정상 작동 경우
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e){ //에러가 난 경우
            model.addAttribute("errorMessage", e.getMessage()); //에러 메시지
            return "join";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginMember(){
        return "login";
    }

    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "login";
    }
}
