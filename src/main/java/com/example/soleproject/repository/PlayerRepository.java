package com.example.soleproject.repository;

import com.example.soleproject.entity.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface PlayerRepository extends CrudRepository<Player,Long> {
    @Override
    ArrayList<Player> findAll();
}
