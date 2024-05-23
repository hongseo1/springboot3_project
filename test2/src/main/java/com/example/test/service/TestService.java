package com.example.test.service;

import com.example.test.entity.Test;

import java.util.Optional;

public interface TestService {
    /** 등록된 모든 퀴즈 정보를 가져옵니다 */
    Iterable<Test> selectAll();

    /** id를 키로 사용해 퀴즈 정보를 한 건 가져옵니다 */
    Optional<Test> selectOneById(Integer id);

    /** 퀴즈 정보를 랜덤으로 한 건 가져옵니다  10장 표 참고 */
    Optional<Test> selectOneRandomTest();

    /** 퀴즈의 정답/오답 여부를 판단합니다 */
    Boolean checkTest(Integer id, Boolean myAnswer);

    /** 퀴즈를 등록합니다 */
    void insertTest(Test test);

    /** 퀴즈를 변경합니다 */
    void updateTest(Test test);

    /** 퀴즈를 삭제합니다 */
    void deleteTestById(Integer id);
}
