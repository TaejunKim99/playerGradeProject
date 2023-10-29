package com.example.soleproject.entity;

import lombok.*;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

// DB가 해당 객체를 인식 가능하게 한다. (해당 클래스로 테이블을 만든다)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Player extends BaseEntity{

    @Id //대푯값을 지정한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 데이터베이스가 id값 생성해줌
    private Long id;

    @Column
    private String league;
    @Column
    private String schedule;
    @Column
    private String content;

    @Column
    private String filename;

    @Column
    private String filepath;


    public void patch(Player player) {
        if(player.league != null)
            this.league = player.league;
        if(player.schedule != null)
            this.schedule = player.schedule;
        if(player.content != null)
            this.content = player.content;

    }
}
