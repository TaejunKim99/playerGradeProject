package com.example.soleproject.api;

import com.example.soleproject.dto.GradeDto;
import com.example.soleproject.entity.Grade;
import com.example.soleproject.service.GradeService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GradeApiController {
    @Autowired
    private GradeService gradeService;

    // 댓글 목록 조회

    @GetMapping("/api/players/{playerId}/grades")
    public ResponseEntity<List<GradeDto>> grades(@PathVariable Long playerId){
        //서비스에게 위임
       List<GradeDto> dtos = gradeService.grades(playerId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);

    }

    // 댓글 생성
    @PostMapping("/api/players/{playerId}/grade")
    public ResponseEntity<GradeDto> create(@PathVariable Long playerId,
                                           @RequestBody GradeDto dto) {
        //서비스에게 위임
        GradeDto createDto = gradeService.create(playerId,dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createDto);

    }


    // 댓글 수정
    @PatchMapping("/api/grades/{id}")
    public ResponseEntity<GradeDto> update(@PathVariable Long id,@RequestBody GradeDto dto){

        //서비스에게 위임
        GradeDto updateDto = gradeService.update(id,dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }
    @DeleteMapping("/api/grades/{id}")
    public ResponseEntity<GradeDto> delete(@PathVariable Long id){

        //서비스에게 위임
        GradeDto deleteDto = gradeService.delete(id);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deleteDto);
    }



    // 댓글 삭제
}
