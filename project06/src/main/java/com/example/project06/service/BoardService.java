package com.example.project06.service;

import com.example.project06.entity.Board;

import java.util.Optional;

public interface BoardService {
    Iterable<Board> selectAll();
    Board selectOneByNo(Integer board_no);
    /*Board selectOneByNo(Integer board_no);*/

    void insertBoard(Board board);
    void updateBoard(Board board);
    void deleteBoardByNo(Integer board_no);
}
