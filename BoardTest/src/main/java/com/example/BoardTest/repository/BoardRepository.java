package com.example.BoardTest.repository;

import com.example.BoardTest.entity.Board;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Integer> {
    /*@Query("SELECT id FROM test WHERE id > 0 ORDER BY id DESC limit 1")
    Integer getRandomId();*/

/*    @Query("SELECT board_no FROM board_test ORDER BY board_no DESC limit 1")
    Integer endId();*/
    @Query("SELECT * FROM board")
    Integer getboard_no();

}
