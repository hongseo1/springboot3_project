package com.example.demo.chapter03.used;

import org.springframework.stereotype.Component;

//@Component
public class EveningGreet implements Greet{
    @Override
    public void greeting() {
        System.out.println("-------------------");
        System.out.println("좋은 저녁입니다.");
        System.out.println("-------------------");
    }
}
