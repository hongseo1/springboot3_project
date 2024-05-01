package com.example.BoardTest.service;

import com.example.BoardTest.entity.Board;

import java.util.Optional;

public interface BoardService {
    Iterable<Board> selectAll(); //등록된 모든 글 목록 정보를 가져옵니다.
    Optional<Board> selectOneByNo(Integer board_no); //id를 키로 사용해 퀴즈 정보를 한 건 가져옵니다.

    void insertBoard(Board board);
    void updateBoard(Board board);
    void deleteBoardByNo(Integer board_no);

}
