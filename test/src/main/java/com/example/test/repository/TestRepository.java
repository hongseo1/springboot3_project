package com.example.test.repository;

import com.example.test.entity.Test;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface TestRepository extends CrudRepository<Test, Integer> {
    /*@Query("SELECT id FROM test ORDER BY RAND() limit 1") //jdbc안에 있는 쿼리문(실제 데이터베이스 언어) 실행해줌
    Integer getRandomId();*/

    @Query("SELECT id FROM test limit 1")
    Integer getAscId();
}
