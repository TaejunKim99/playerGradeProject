package com.example.soleproject.dto;

import com.example.soleproject.entity.Grade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class GradeDto {
    private Long id;
    private Long playerId;
    private String playerName;
    private String grade;
    private String body;

    public static GradeDto createGradeDto(Grade grade) {
        return new GradeDto(
              grade.getId(),
                grade.getPlayer().getId(),
                grade.getPlayerName(),
                grade.getGrade(),
                grade.getBody()
        );
    }
}
