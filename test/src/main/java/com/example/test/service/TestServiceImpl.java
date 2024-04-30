package com.example.test.service;

import com.example.test.entity.Test;
import com.example.test.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@Transactional
public class TestServiceImpl implements TestService{
    @Autowired
    TestRepository repository;

    @Override
    public Iterable<Test> selectAll() {
        return repository.findAll();
    }

    Integer count= -1;
    @Override
    public Optional<Test> selectOneById(Integer id) {
        Integer startId;
        Integer endId = repository.endId();
        count++;
        startId = count;
        if(startId > endId){ //마지막 문제인지 확인
            count = -1; //마지막이면 다시 초기화
            startId = count;
        }
        Boolean findId = repository.existsById(startId); //존재 여부 확인
        while(findId == false){ //startId이 존재하지 않을 때 실행
            startId++;
            count = startId;
            findId = repository.existsById(startId);
        }
        return repository.findById(startId);
    }

    @Override
    public Optional<Test> selectOneRandomTest() {
        Integer randId = repository.getRandomId();
        if(randId == null){
            return Optional.empty();
        }
        /*
        randId=0;
        while (count < 100){ //수정 필요
            count++;
            randId=count+randId;
            if(randId>=4){ //수정 필요
                return repository.findById(randId+1);
            } else {
                return repository.findById(randId);
            }
        }
        */
        return repository.findById(randId);
    }

    @Override
    public Boolean checkTest(Integer id, Boolean myAnswer) {
        Boolean check = false;
        Optional<Test> optTest = repository.findById(id);
        if(optTest.isPresent()){
            Test test = optTest.get();
            if(test.getAnswer().equals(myAnswer)){
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
