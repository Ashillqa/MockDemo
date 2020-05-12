package com.qa.rest;

import com.qa.domain.Player;
import com.qa.dto.PlayerDTO;
import com.qa.service.playerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {

    private final playerService service;

 ////////////// constructor ///////////////////////////////////////////////
    @Autowired
    public PlayerController(playerService service) {
        this.service = service;
    }

 //////////////////// get all players DTO //////////////////////////////////////////////
    @GetMapping("/getAllPlayers")
    public ResponseEntity<List<PlayerDTO>> getAllPlayers(){
        return ResponseEntity.ok(this.service.readPlayer());
    }

 ////////////////////////////// get players by id DTO ////////////////////////////////////////////
    @GetMapping("/getPlayerById/{id}")
    public ResponseEntity<PlayerDTO> playerById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.findPlayerById(id));
    }

 ///////////////////////create player DTO //////////////////////////////////////////
    @PostMapping("/createPlayer")
    public ResponseEntity<PlayerDTO> createPlayer(@RequestBody Player player){
        return new ResponseEntity<PlayerDTO>(this.service.createPlayer(player), HttpStatus.CREATED);
    }

 ///////////////////////////// delete player DTO ///////////////////////////////////////
    @DeleteMapping("/deletePlayer/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable Long id){
        return this.service.deletePlayer(id)
                ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                : ResponseEntity.noContent().build();
    }

 //////////////////////// update player DTO ///////////////////////////////////////////////////
    @PutMapping("/updatePlayer/{id}")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable Long id, @RequestBody Player player){
        return ResponseEntity.ok(this.service.updatePlayer(id, player));
    }
}
