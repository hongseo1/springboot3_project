package com.example.project06.repository;

import com.example.project06.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Integer> {
    @Query("SELECT * FROM board ORDER BY board_no DESC")
    Integer getboard_no();

    Page<Board> findAll(Pageable pageable);
}