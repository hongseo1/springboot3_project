package com.example.project06.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "board")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @Column(name = "board_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardNo;
    private String title;
    private String content;
    private String writer;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate regdate;


}