package com.example.project06.service;

import com.example.project06.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    //Iterable<Board> getList();
    Board selectOneByNo(Integer boardNo);
    /*Board selectOneByNo(Integer board_no);*/

    void insertBoard(Board board);
    void updateBoard(Board board);
    void deleteBoardByNo(Integer boardNo);

    Page<Board> getList(Pageable pageable);

    public Page<Board> boardSearchList(String searchKeyword,Pageable pageable);
}