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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
public class BoardController {
    @Autowired
    BoardService service;

    @GetMapping("board")
    public String showBoard(BoardForm boardForm, Model model, @PageableDefault(sort = "boardNo", direction = Sort.Direction.DESC) Pageable pageable){
        //@RequestParam(value="page", defaultValue = "0") int page
        //@PageableDefault(sort = "boardNo", direction = Sort.Direction.DESC) Pageable pageable
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

    @GetMapping("/insert")
    public String insert(@Validated BoardForm boardForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Board board = new Board();
        board.setTitle(boardForm.getTitle());
        board.setContent(boardForm.getContent());
        board.setWriter(boardForm.getWriter());
        LocalDate regdate = LocalDate.now();
        board.setRegdate(regdate);

        if(!bindingResult.hasErrors()){
            service.insertBoard(board);
            return "redirect:/board";
        }else {
            redirectAttributes.addFlashAttribute("complete", "게시글을 입력해 주세요.");
            return "redirect:/create";
        }
        /*service.insertBoard(board);
        return "redirect:/board";*/
    }

    @GetMapping("/detail/{boardNo}")
    public String detail(@PathVariable Integer boardNo, Model model){
        model.addAttribute("board", service.selectOneByNo(boardNo));

        return "board_detail";
    }

    @GetMapping("/boardUpdate/{boardNo}")
    public String showUpdate(@PathVariable Integer boardNo, Model model){
        model.addAttribute("board", service.selectOneByNo(boardNo));
        return "board_update";
    }

    @GetMapping("/update/{boardNo}")
    public String update(@PathVariable Integer boardNo, Board board, Model model){
        Board board1 = service.selectOneByNo(boardNo);

        board1.setTitle(board.getTitle());
        board1.setContent(board.getContent());

        LocalDate regdate = LocalDate.now();
        board1.setRegdate(regdate);

        service.updateBoard(board1);

        model.addAttribute("board", board1);
        /*
        model.addAttribute("board", service.selectOneByNo(boardNo));

        board.setTitle(board.getTitle());
        board.setContent(board.getContent());
        //board.setWriter(board.getWriter());
        LocalDate regdate = LocalDate.now();
        board.setRegdate(regdate);
        service.updateBoard(board);
*/
        return "redirect:/detail/{boardNo}";
    }

    @GetMapping("/board/delete")
    public String delete(Integer boardNo){
        service.deleteBoardByNo(boardNo);
        return "redirect:/board";
    }
}