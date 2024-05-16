package com.example.project06.form;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardForm {
    @Column(name = "board_no")
    private Integer boardNo;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String writer;
    private LocalDate regdate;
    private  Boolean newBoard;
}