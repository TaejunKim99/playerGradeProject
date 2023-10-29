package com.example.soleproject.entity;

import com.example.soleproject.dto.GradeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Grade extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //해당 댓글 엔티티가 여러개가 하나의 player에 연관된다.
    @JoinColumn(name = "plid") // 포링키 일듯
    private Player player;

    @Column
    private String playerName;

    @Column
    private String grade;

    @Column
    private String body;

    public static Grade createGrade(GradeDto dto, Player player) {
        // 예외 처리
        if(dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야합니다.");
        if(dto.getPlayerId() != player.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못되었습니다.");
        // 엔티티 생성 및 반환
        return new Grade(
                dto.getId(),
                player,
                dto.getPlayerName(),
                dto.getGrade(),
                dto.getBody()
        );
    }

    public void patch(GradeDto dto) {
        //예외 발생
        if(this.id != dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패 잘못된 id가 입력되었습니다.");

        // 객체를 갱신
        if (dto.getPlayerName() != null)
            this.playerName=dto.getPlayerName();
        if (dto.getBody() != null)
            this.body = dto.getBody();
        if (dto.getGrade() != null)
            this.grade = dto.getGrade();
    }
}
