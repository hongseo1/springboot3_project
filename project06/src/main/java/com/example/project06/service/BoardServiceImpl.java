package com.example.project06.service;

import com.example.project06.entity.Board;
import com.example.project06.repository.BoardRepository;
import com.example.project06.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BoardServiceImpl implements BoardService{
    @Autowired(required=false)
    BoardRepository repository;

    @Override
    public Iterable<Board> getList() {
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

    public Page<Board> getList(Pageable pageable){
        //Pageable pageable = PageRequest.of(page, 10);
        return this.repository.findAll(pageable);
    }
}