package com.example.project06.controller;

import com.example.project06.entity.Board;
import com.example.project06.form.BoardForm;
import com.example.project06.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
public class BoardController {
    @Autowired
    BoardService service;

    @GetMapping("board")
    public String showBoard(BoardForm boardForm, Model model, @PageableDefault(sort = "board_no", direction = Sort.Direction.DESC) Pageable pageable){
        //@RequestParam(value="page", defaultValue = "0") int page
        //@PageableDefault(sort = "board_no", direction = Sort.Direction.DESC) Pageable pageable
        boardForm.setNewBoard(true);

        Page<Board> list = this.service.getList(pageable);
        Iterable<Board> listV = service.getList();

        model.addAttribute("list", list);
        model.addAttribute("listV", listV);
        return "board";
    }

    @GetMapping("/create")
    public String showWriter(BoardForm boardForm, Model model){
        boardForm.setNewBoard(true); //등록인지 아닌지
        model.addAttribute("title", "등록 폼");

        return "board_writer";
    }

    @PostMapping("/insert")
    public String insert(@Validated BoardForm boardForm) {
        Board board = new Board();
        board.setTitle(boardForm.getTitle());
        board.setContent(boardForm.getContent());
        board.setWriter(boardForm.getWriter());
        LocalDate regdate = LocalDate.now();
        board.setRegdate(regdate);

        /*if(!bindingResult.hasErrors()){
            service.insertBoard(board);
            redirectAttributes.addFlashAttribute("complete", "등록이 완료되었습니다.");
            return "redirect:/board";
        }else {
            return showBoard(boardForm, model);
        }*/
        service.insertBoard(board);
        return "redirect:/board";

    }

    @GetMapping("/detail/{board_no}")
    public String detail(@PathVariable Integer board_no, Model model){
        model.addAttribute("board", service.selectOneByNo(board_no));

        return "board_detail";
    }

    @GetMapping("/boardUpdate/{board_no}")
    public String showUpdate(@PathVariable Integer board_no, Model model){
        model.addAttribute("board", service.selectOneByNo(board_no));
        return "board_update";
    }

    @PostMapping("/update/{board_no}")
    public String update(@PathVariable Integer board_no, Board board, Model model){
        model.addAttribute("board", service.selectOneByNo(board_no));

        board.setTitle(board.getTitle());
        board.setContent(board.getContent());
        board.setWriter(board.getWriter());
        LocalDate regdate = LocalDate.now();
        board.setRegdate(regdate);
        service.updateBoard(board);
        return "redirect:/detail/{board_no}";
    }

    @GetMapping("/board/delete")
    public String delete(Integer board_no){
        service.deleteBoardByNo(board_no);
        return "redirect:/board";
    }
}