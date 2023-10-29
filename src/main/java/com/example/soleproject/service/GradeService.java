package com.example.soleproject.service;

import com.example.soleproject.dto.GradeDto;
import com.example.soleproject.entity.Grade;
import com.example.soleproject.entity.Player;
import com.example.soleproject.repository.GradeRepository;
import com.example.soleproject.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeService {
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private PlayerRepository playerRepository;


    public List<GradeDto> grades(Long playerId) {
        // 조회 : 댓글 목록
/*        List<Grade> grades = gradeRepository.findByPlayerId(playerId);
        // 변환 : 엔티티 -> DTO
        List<GradeDto> dtos = new ArrayList<GradeDto>();
        for(int i = 0;i<grades.size();i++){
            Grade c = grades.get(i);
            GradeDto dto = GradeDto.createGradeDto(c);
            dtos.add(dto);
        }*/
        // 반환
        return gradeRepository.findByPlayerId(playerId)
                .stream()
                .map(grade -> GradeDto.createGradeDto(grade))
                .collect(Collectors.toList());
    }

    @Transactional
    public GradeDto create(Long playerId, GradeDto dto) {
        // 게시글 조회 및 예외발생
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));
        // 댓글 엔티티 생성
        Grade grade = Grade.createGrade(dto,player);
        // 댓글 엔티티를 DB로 저장
        Grade created = gradeRepository.save(grade);
        // DTO로 변경하여 반환
        return GradeDto.createGradeDto(created);
    }

    @Transactional
    public GradeDto update(Long id, GradeDto dto) {
        // 댓글 조회 및 예외 발생
          Grade target = gradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(("대상 댓글이 없습니다. ")));

        // 댓글 수정
         target.patch(dto);
        // DB로 갱신
        Grade update = gradeRepository.save(target);
        // 댓글 엔티티를 DTO를 변환 및 반환
        return GradeDto.createGradeDto(update);
    }

    @Transactional
    public GradeDto delete(Long id) {
        // 댓글 조회(및 예외 발생)
        Grade target = gradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("대상이 없습니다."));
        // DB 댓글 삭제
        gradeRepository.delete(target);
        // 삭제 댓글을 DTO로 변환
        return GradeDto.createGradeDto(target);
    }
}
