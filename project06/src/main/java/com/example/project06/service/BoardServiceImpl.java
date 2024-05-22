package com.example.project06.service;

import com.example.project06.entity.Board;
import com.example.project06.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;

    /*@Override
    public Iterable<Board> getList() {
        return boardRepository.findAll();
    }*/

    @Override
    public Board selectOneByNo(Integer boardNo) {
        return boardRepository.findById(boardNo).get();
    }

    @Override
    public void insertBoard(Board board) {
        boardRepository.save(board);
    }

    @Override
    public void updateBoard(Board board) {
        boardRepository.save(board);
    }

    @Override
    public void deleteBoardByNo(Integer boardNo) {
        boardRepository.deleteById(boardNo);
    }

    public Page<Board> getList(Pageable pageable){
        //Pageable pageable = PageRequest.of(page, 10);
        return this.boardRepository.findAll(pageable);
    }

    public Page<Board> boardSearchList(String searchKeyword,Pageable pageable){
        return boardRepository.findByTitleContaining(searchKeyword,pageable);
    }


}