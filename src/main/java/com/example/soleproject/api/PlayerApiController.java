package com.example.soleproject.api;

import com.example.soleproject.dto.PlayerForm;
import com.example.soleproject.entity.Player;
import com.example.soleproject.repository.PlayerRepository;
import com.example.soleproject.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
@Slf4j
@RestController // REST API 용 컨트롤러 ! 데이터를 반환

public class PlayerApiController {
    @Autowired
    private PlayerService playerService;

    // GET
    @GetMapping("/api/players")
    public List<Player> index(){
        return playerService.index();
    }

    @GetMapping("/api/player/{id}")
    public Player show(@PathVariable Long id){
        return playerService.findPlayer(id);
    }
    // POST
    @PostMapping("/api/player/create")
    // @RequestBody = JSON데이터 받기
    public ResponseEntity<Player> create(@RequestBody PlayerForm dto){
        Player created = playerService.savePlayer(dto);
        return (created != null)
                ? ResponseEntity.status(HttpStatus.OK).body(created)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    // PATCH
    @PatchMapping("/api/player/{id}/update")
    // ResponseEntity를 사용하면 상태코드를 같이 보낼 수 있다.
    public ResponseEntity<Player> update(@RequestBody PlayerForm dto, @PathVariable Long id){
        Player update = playerService.update(dto,id);
        return (update != null)
                ? ResponseEntity.status(HttpStatus.OK).body(update)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    // DELETE
    @DeleteMapping("/api/player/{id}/delete")
    public ResponseEntity<Player> delete(@PathVariable Long id){
       Player deleted = playerService.delete(id);
        return (deleted != null)
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
