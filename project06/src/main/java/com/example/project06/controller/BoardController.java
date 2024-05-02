package com.example.project06.controller;

import com.example.project06.entity.Board;
import com.example.project06.form.BoardForm;
import com.example.project06.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class BoardController {
    @Autowired
    BoardService service;

    @GetMapping("board")
    public String showBoard(BoardForm boardForm, Model model){
        boardForm.setNewBoard(true);
        Iterable<Board> list = service.selectAll();

        model.addAttribute("list", list);
        return "board";
    }
    @GetMapping("/create")
    public String showWriter(BoardForm boardForm, Model model){
        boardForm.setNewBoard(true); //등록인지 아닌지

        model.addAttribute("title", "등록 폼");

        return "board_writer";
    }
    @GetMapping("detail/{board_no}")
    public String detail(@Validated BoardForm boardForm, BindingResult result, Model model, RedirectAttributes redirectAttributes){




        Board board = makeBoard(boardForm);
        return "redirect:/detail" + board.getBoard_no();
    }

    @PostMapping("/insert")
    public String insert(Board board) {
        //@Validated BoardForm boardForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes
        /*
        Board board = new Board();
        board.setTitle(boardForm.getTitle());
        board.setContent(boardForm.getContent());
        board.setWriter(boardForm.getWriter());
        board.setRegdate(boardForm.getRegdate());

        if(!bindingResult.hasErrors()){
            service.insertBoard(test);
            redirectAttributes.addFlashAttribute("complete", "등록이 완료되었습니다.");
            return "redirect:/board";
        }else {
            return showBoard(boardForm, model);
        }
        */
        service.insertBoard(board);
        return "redirect:/board";
    }

    @GetMapping("create/{board_no}")
    public String showUpdate(BoardForm boardForm, @PathVariable Integer board_no, Model model){
        Optional<Board> boardOpt = service.selectOneByNo(board_no);
        Optional<BoardForm> boardFormOpt = boardOpt.map(b -> makeBoardForm(b)); //(arrow함수) 함수가 두번 실행
        if(boardFormOpt.isPresent()){
            boardForm = boardFormOpt.get();
        }
        makeUpdateModel(boardForm, model);
        return "board_writer";
    }

    private void makeUpdateModel(BoardForm boardForm, Model model){
        model.addAttribute("board_no", boardForm.getBoard_no());
        boardForm.setNewBoard(false);
        model.addAttribute("boardForm", boardForm); //최종 수정본을 testForm에 전달(저장)
        model.addAttribute("title", "변경 폼");
    }

    @PostMapping("/update")
    public String update(@Validated BoardForm boardForm, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        Board board = makeBoard(boardForm);
        if(!result.hasErrors()){
            service.updateBoard(board);
            redirectAttributes.addFlashAttribute("complete", "변경이 완료되었습니다.");
            return "redirect:/board" + board.getBoard_no();
        }else {
            makeUpdateModel(boardForm, model);
            return "board";
        }
    }

    private Board makeBoard(BoardForm boardForm){
        Board board = new Board();
        board.setBoard_no(boardForm.getBoard_no());
        board.setTitle(boardForm.getTitle());
        board.setContent(boardForm.getContent());
        board.setWriter(boardForm.getWriter());
        board.setRegdate(board.getRegdate());
        return board;
    }

    private BoardForm makeBoardForm(Board board){
        BoardForm form = new BoardForm();
        form.setBoard_no(board.getBoard_no());
        form.setTitle(board.getTitle());
        form.setContent(board.getContent());
        form.setWriter(board.getWriter());
        form.setRegdate(board.getRegdate());
        form.setNewBoard(false);
        return form;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("board_no") String board_no, Model model, RedirectAttributes redirectAttributes){
        service.deleteBoardByNo(Integer.parseInt(board_no));
        redirectAttributes.addFlashAttribute("delcomplete", "삭제 완료했습니다.");
        return "redirect:/board";
    }
}
