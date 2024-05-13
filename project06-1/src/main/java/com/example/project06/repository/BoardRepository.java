package com.example.project06.repository;

import com.example.project06.entity.Board;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface BoardRepository extends CrudRepository<Board, Integer> {
    @Query("SELECT * FROM board")
    Integer getboard_no();

    Page<Board> findAll(Pageable pageable);
}