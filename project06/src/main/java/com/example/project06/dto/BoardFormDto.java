package com.example.project06.dto;

import com.example.project06.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardFormDto {
    @Column(name = "board_no")
    private Integer boardNo;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    /*@NotBlank
    private String writer;*/

    @ManyToOne
    @JoinColumn(name = "member_email")
    private Member member;
    private LocalDate regdate;
    private  Boolean newBoard;
}