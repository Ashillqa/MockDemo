package com.qa.rest;

import com.qa.domain.Player;
import com.qa.service.playerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {

    private final playerService service;

    @Autowired
    public PlayerController(playerService service) {
        this.service = service;
    }
    @GetMapping("/getAllPlayers")
    public List<Player> getAllPlayers(){
        return this.service.readPlayers();
    }

    @GetMapping("/getPlayerById/{id}")
    public Player getPlayerById(@PathVariable Long id){
        return this.service.findPlayerById(id);
    }

    @PostMapping("/createPlayer")
    public Player createPlayer(@RequestBody Player player){
        return this.service.createPlayer(player);
    }

    @DeleteMapping("/deletePlayer/{id}")
    public boolean deletePlayer(@PathVariable Long id){
        return this.service.deletePlayer(id);
    }

    @PutMapping("/updatePlayer/{id}")
    public Player updatePlayer(@PathVariable Long id,@RequestBody Player player){
        return this.service.updatePlayer(id,player);
    }
}
