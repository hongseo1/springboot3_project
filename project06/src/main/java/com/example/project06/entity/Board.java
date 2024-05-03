package com.example.project06.entity;

import com.example.project06.form.BoardForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    private Integer board_no;
    private String title;
    private String content;
    private String writer;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate regdate;


}