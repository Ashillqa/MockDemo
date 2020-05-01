package com.qa.service;

import com.qa.domain.Player;
import com.qa.exception.PlayerNotFoundException;
import com.qa.repo.playerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class playerService {
    private final playerRepository repo;

    @Autowired
    public playerService(playerRepository repo) {
        this.repo = repo;
    }

    public List<Player> readPlayers(){
        return this.repo.findAll();
    }

    public Player createPlayer(Player player){
        return this.repo.save(player);
    }

    public Player findPlayerById(Long id){
        return this.repo.findById(id).orElseThrow(PlayerNotFoundException::new);
    }

    public Player updatePlayer(Long id, Player player){
        Player update = findPlayerById(id);
        update.setName(player.getName());
        update.setGoals(player.getGoals());
        update.setAssists(player.getAssists());
        return this.repo.save(update);
    }
    public boolean deletePlayer(Long id){
        if(!this.repo.existsById(id)){
            throw new PlayerNotFoundException();
        }
        this.repo.deleteById(id);
        return this.repo.existsById(id);
    }

}
