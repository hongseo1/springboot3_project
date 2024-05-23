package com.example.test.service;

import com.example.test.entity.Test;
import com.example.test.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TestServiceImpl implements TestService {
    @Autowired
    TestRepository repository;

    @Override
    public Iterable<Test> selectAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Test> selectOneById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Test> selectOneRandomTest() {
        // 랜덤으로 id 값을 가져오기
        // getRandomId() 는 따로 작성해야 되는 항목이라서 현재 에러표시 됨
        Integer randId = repository.getRandomId();

        // 퀴즈가 없는 경우
        if (randId == null) {
            // 빈 Optional 인스턴스를 반환
            return Optional.empty();
        }
        return repository.findById(randId);
    }

    @Override
    public Boolean checkTest(Integer id, Boolean myAnswer) {
        // 퀴즈 정답/오답 판단용 변수
        Boolean check = false;

        // 대상 퀴즈를 가져오기
        Optional<Test> optTest = repository.findById(id);

        // 퀴즈를 가져왔는지 확인
        if (optTest.isPresent()) {
            Test test = optTest.get();
            // 퀴즈 정답 확인
            if(test.getAnswer().equals(myAnswer)) {
                check = true;
            }
        }
        return check;
    }

    @Override
    public void insertTest(Test test) {
        repository.save(test);
    }

    @Override
    public void updateTest(Test test) {
        repository.save(test);
    }

    @Override
    public void deleteTestById(Integer id) {
        repository.deleteById(id);
    }
}
