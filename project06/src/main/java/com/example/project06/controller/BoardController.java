package com.example.project06.controller;

import com.example.project06.entity.Board;
import com.example.project06.dto.BoardFormDto;
import com.example.project06.entity.Member;
import com.example.project06.repository.MemberRepository;
import com.example.project06.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDate;

@Controller
public class BoardController {
    @Autowired
    BoardService service;
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("board")
    public String showBoard(BoardFormDto boardFormDto, Model model, @PageableDefault(sort = "boardNo", direction = Sort.Direction.DESC) Pageable pageable, String searchKeyword){
        //@RequestParam(value="page", defaultValue = "0") int page
        //@PageableDefault(sort = "boardNo", direction = Sort.Direction.DESC) Pageable pageable
        boardFormDto.setNewBoard(true);
        Page<Board> list = null;

        if (searchKeyword == null) {
            // 검색 단어가 없으면 기존 화면을 보여준다.
            list = service.getList(pageable);
        } else {
            // 검색 단어가 들어오면 검색 단어에 맞게 나온다. 쿼리스트링으로 들어가는 키워드를 찾아냄
            list = service.boardSearchList(searchKeyword, pageable);
            if(list.isEmpty()){
                list = service.getList(pageable);
                model.addAttribute("searchErrorMsg", "검색 결과가 없습니다.");
            }
        }

        //Page<Board> list = this.service.getList(pageable);
        //Iterable<Board> listV = service.getList();

        model.addAttribute("list", list);
        //model.addAttribute("listV", listV);

        return "board";
    }

    @GetMapping("/create")
    public String showWriter(BoardFormDto boardFormDto, Model model, Principal principal, ModelMap modelMap){
        boardFormDto.setNewBoard(true); //등록인지 아닌지
        model.addAttribute("title", "등록 폼");
        String loginId = principal.getName();
        Member member = memberRepository.findByEmail(loginId);
        modelMap.addAttribute("member", member);

        return "board_writer";
    }

    @PostMapping("/insert")
    public String insert(@Validated BoardFormDto boardFormDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal) {
        String loginId = principal.getName();
        Member member = memberRepository.findByEmail(loginId);
        boardFormDto.setMember(member);
        Board board = new Board();
        board.setTitle(boardFormDto.getTitle());
        board.setContent(boardFormDto.getContent().replace("\r\n","<br>"));
        board.setMember(boardFormDto.getMember());
        LocalDate regdate = LocalDate.now();
        board.setRegdate(regdate);

        if(!bindingResult.hasErrors()){
            service.insertBoard(board);
            return "redirect:/board";
        }else {
            redirectAttributes.addFlashAttribute("complete", "게시글을 입력해 주세요.");
            return "redirect:/create";
        }
    }

    @GetMapping("/detail/{boardNo}")
    public String detail(@PathVariable Integer boardNo, Model model, Principal principal){
        if(principal != null){
            String loginId = principal.getName();
            Member member = memberRepository.findByEmail(loginId);
            model.addAttribute("member", member);
        }
        Board board = service.selectOneByNo(boardNo);
        board.setContent(board.getContent().replace("<br>","<br>"));
        model.addAttribute("board", board);

        return "board_detail";
    }

    @GetMapping("/boardUpdate/{boardNo}")
    public String showUpdate(@PathVariable Integer boardNo, Model model){
        Board board = service.selectOneByNo(boardNo);
        board.setContent(board.getContent().replace("<br>","\r\n"));
        model.addAttribute("board", board);
        return "board_update";
    }

    @GetMapping("/update/{boardNo}")
    public String update(@PathVariable Integer boardNo, Board board, Model model){
        Board board1 = service.selectOneByNo(boardNo);

        board1.setTitle(board.getTitle());
        board1.setContent(board.getContent().replace("\r\n","<br/>"));

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