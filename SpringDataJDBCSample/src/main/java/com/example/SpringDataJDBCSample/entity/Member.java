package com.example.SpringDataJDBCSample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Member { //entity에는 테이블이 들어감 그래서 클래스를 테이블 명으로..
//@Data: 클래스에 부여하는 것으로, 모든 필드에 대해 getter/setter를 액세스.
//@NoArgsConstructor: 클래스에 부여하는 것으로, 기본 생성자가 자동으로 생성된다.
//@AllArgsConstructor: 모든 필드에 대해 초기화 값을 인수로 가지는 생성자가 자동 생성.
//@Id: 기본키(Primary key)에 해당하는 id 필드에 대해 @Id 어노테이션 부여
    @Id
    private  Integer id;
    private  String name;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
