package com.example.soleproject.dto;

import com.example.soleproject.entity.Player;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
    public class PlayerForm {

        private Long id;
        private String league;
        private String schedule;
        private String content;

        private String filename;

        private String filepath;


    public Player toEntity() {
        return new Player(id,league,schedule,content,filename,filepath);
    }
}
