package com.example.soleproject.service;


import com.example.soleproject.dto.PlayerForm;
import com.example.soleproject.entity.Player;
import com.example.soleproject.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service // 서비스 선언 !  (서비스 객체를 스프링부트에 생성)
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> index() {
        return playerRepository.findAll();
    }

    public Player findPlayer(Long id) {
        return playerRepository.findById(id).orElse(null);
    }

    public Player savePlayer(PlayerForm dto) {
        Player player = dto.toEntity();
        if(player.getId() != null){
            return null;
        }
        return playerRepository.save(player);
    }

    public Player update(PlayerForm dto, Long id) {
        Player player = dto.toEntity();
        Player target = playerRepository.findById(id).orElse(null);
        if(target == null || id != player.getId()){
            return null;
        }
         target.patch(player);
        Player update = playerRepository.save(target);
        return update;
    }

    public Player delete(Long id) {
        Player target = playerRepository.findById(id).orElse(null);
        if(target == null){
            return null;
        }
        playerRepository.delete(target);
        return target;
    }

    //파일 저장을 위한 메소드
    public void saveFile(Player player, MultipartFile file) throws Exception {
        // 프로젝트 경로 저장
        String Path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img";
        // 랜덤 이름 저장
        UUID uuid = UUID.randomUUID();
        // 랜덤이름을 파일 이름앞에 붙이기
        String fileName = uuid +"_"+file.getOriginalFilename();
        // Path에 name이라는 이름을 저장
        File saveFile = new File(Path,fileName);
        // 파일 저장
        file.transferTo(saveFile);
        //파일 이름 저장
        player.setFilename(fileName);
        //파일 경로 저장
        player.setFilepath("/img/"+fileName);
        playerRepository.save(player);
    }


}
