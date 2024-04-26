package com.example.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor //파라미터가 없는 디폴트 생성자를 자동으로 생성
@AllArgsConstructor //클래스의 모든 필드 값을 파라미터로 받는 생성자를 자동으로 생성
public class Test {
    @Id
    private Integer id;
    private String question;
    private Boolean answer;
    private String author;
}
