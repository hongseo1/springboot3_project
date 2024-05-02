package com.example.BoardTest.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    private Integer board_no;
    private String title;
    private String content;
    private String writer;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date regdate;
}
