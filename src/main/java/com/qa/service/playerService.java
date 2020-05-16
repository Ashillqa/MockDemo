package com.qa.service;

import com.qa.domain.Player;
import com.qa.dto.PlayerDTO;
import com.qa.exception.PlayerNotFoundException;
import com.qa.repo.playerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class playerService {
    private final playerRepository repo;
    private final ModelMapper mapper;

    @Autowired
    public playerService(playerRepository repo,ModelMapper mapper) {
        this.repo = repo;
        this.mapper=mapper;
    }
    // making a map to Dto for employee to convert types to be passed to controller
    private PlayerDTO mapToDto(Player player){
        return this.mapper.map(player, PlayerDTO.class);
    }

    ///////////////////list all players but as DTO///////////////////////////////////////////
    public List<PlayerDTO> readPlayer(){
        return this.repo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    ///////////////////create player as DTO//////////////////////////////////////
    public PlayerDTO createPlayer(Player player){
        Player tempPlay = this.repo.save(player);
        return this.mapToDto(tempPlay);
    }

    ///////////////////////////find player by id DTO//////////////////////////////////////
    public PlayerDTO findPlayerById(Long id){
        return this.mapToDto(this.repo.findById(id).orElseThrow(PlayerNotFoundException::new));
    }

    /////////////////////////////update player DTO////////////////////////////////////////////
    public PlayerDTO updatePlayer(Long id, Player player){
        Player update = this.repo.findById(id).orElseThrow(PlayerNotFoundException::new);
        update.setGoals(player.getGoals());
        return this.mapToDto(this.repo.save(player));
    }

    ///////////////////////////////delete player DTO//////////////////////////////////////////
    public boolean deletePlayer(Long id){
        if(!this.repo.existsById(id)){
            throw new PlayerNotFoundException();
        }
        this.repo.deleteById(id);
        return this.repo.existsById(id);
    }

}
