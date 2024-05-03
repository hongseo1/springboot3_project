package com.example.project06.service;

import com.example.project06.entity.Board;
import com.example.project06.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BoardServiceImpl implements BoardService{
    @Autowired
    BoardRepository repository;

    @Override
    public Iterable<Board> selectAll() {
        return repository.findAll();
    }

    @Override
    public Board selectOneByNo(Integer board_no) {
        return repository.findById(board_no).get();
    }

    @Override
    public void insertBoard(Board board) {
        repository.save(board);
    }

    @Override
    public void updateBoard(Board board) {
        repository.save(board);
    }

    @Override
    public void deleteBoardByNo(Integer board) {
        repository.deleteById(board);
    }
}
