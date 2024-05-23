package com.example.test.repository;

import com.example.test.entity.Test;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface TestRepository extends CrudRepository<Test, Integer> {
    @Query("SELECT id FROM test WHERE id > 0 order by id desc limit 1")
    Integer getRandomId();
}
