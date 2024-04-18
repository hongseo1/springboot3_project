package com.example.SpringMVCViewSample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("hello") //Controller에 들어온 요청을 특정 메서드와 매핑하기 위해 사용하는 것 (url에 hello가 들어오면 실행)
//@RequestMapping(value = "hello") / @RequestMapping(value = {"hello", "hellospring"}) @GetMapping도 동일. (보통은 한개 사용/ 여러개 사용 시 {})
//@RequestMapping(value = "hello", method = RequestMethod.GET) form 태그의 method 설정 가능(=데이터 전송 방식 지정 가능)
//@RequestMapping(value = "hello", method = {RequestMethod.GET, RequestMethod.POST}) get, post 두개 사용 가능
public class HelloViewController {
    @GetMapping("view")
    public String helloView(){
        return "hello";
    }
    @GetMapping("view2")
    public String morningView(){
        return "morning";
    }
}
//문제) http://localhost:8080/hello/view2를 입력하면 morning가 출력되게 만들어 보세요.
//문제) http://localhost:8080/hello/view2 또는 view를 입력하면 Hello View!!!가 출력되게 만들어 보세요.
//     = @GetMapping{"view", "view2"} 사용
