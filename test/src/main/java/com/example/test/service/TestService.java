package com.example.test.service;

import com.example.test.entity.Test;

import java.util.Optional;

public interface TestService {
    Iterable<Test> selectAll();
    //등록된 모든 퀴드의 정보를 가져옵니다.
    Optional<Test> selectOneById(Integer id);
    //id를 키로 사용해 퀴즈 정보를 한 건 가져옵니다.
    //Optional<Test> selectOneRandomTest();
    Optional<Test> selectOneTest();
    //퀴즈 정보를 랜덤을 한 건 가져옵니다.
    Boolean checkTest(Integer id, Boolean myAnswer);
    //퀴즤의 정답, 오답 여부를 판단합니다.

    void insertTest(Test test);
    // 퀴즈를 등록합니다.
    void updateTest(Test test);
    // 퀴즈를 변경합니다.
    void deleteTestById(Integer id);
    // 퀴즈를 삭제합니다.
}
