package com.example.project06.repository;

import com.example.project06.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    /*@Query("SELECT * FROM board")
    Integer getboard_no();*/

    Page<Board> findAll(Pageable pageable);
}