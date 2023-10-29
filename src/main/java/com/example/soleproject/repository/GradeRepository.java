package com.example.soleproject.repository;

import com.example.soleproject.entity.Grade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade,Long> {
    // 특정 게시글의 모든 댓글 조회
    @Query(value =
            "SELECT * " +
            "FROM Grade " +
            "WHERE plid = :playerId",
            nativeQuery = true)
    List<Grade> findByPlayerId(@Param("playerId") Long playerId);
    // 특정 선수의 모든 펑점 조회
    List<Grade> findByPlayerName(String playerName);



}
