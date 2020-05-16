package com.qa.service;

import com.qa.domain.Category;
import com.qa.domain.Player;
import com.qa.dto.CatDTO;
import com.qa.exception.CatNotFoundException;
import com.qa.repo.catRepository;
import com.qa.repo.playerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class catService {

    private final catRepository repo;
    private final playerRepository playRepo;

    private final ModelMapper mapper;

    @Autowired
    public catService(catRepository repo, playerRepository playRepo, ModelMapper mapper) {
        this.repo = repo;
        this.playRepo = playRepo;
        this.mapper = mapper;
    }
//////////////mapper is what allows us to convert to the object of type DTO from the normal cat or emp///////
    private CatDTO mapToDto(Category category){
        return this.mapper.map(category, CatDTO.class);
    }
////////////////////////////////read all categories //////////////////////////////////////////////////
    public List<CatDTO> readCat(){
        return this.repo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }
///////////////////////////create a category ///////////////////////////////////////////////////
    public CatDTO createCat(Category category){
        return this.mapToDto(this.repo.save(category));
    }

    public CatDTO findCatById(Long id){
        return this.mapToDto(this.repo.findById(id).orElseThrow(CatNotFoundException::new));
    }
/////////////////////////update a category to change the players in it nothing else needs to change////////////
    public CatDTO updateCat(Long id, Category category){
        Category update = this.repo.findById(id).orElseThrow(CatNotFoundException::new);
        update.setPlayers(category.getPlayers());
        return this.mapToDto(this.repo.save(update));
    }
//////////////////delete a category which returns false after looking for it again/////////////////
    public boolean deleteCat(Long id){
        if(!this.repo.existsById(id)){
            throw new CatNotFoundException();
        }
        this.repo.deleteById(id);
        return this.repo.existsById(id);
    }
////////////////////////add a player to a category////////////////////////////////
    public CatDTO addPlayerToCat(Long id, Player player){
        Category category = this.repo.findById(id).orElseThrow(CatNotFoundException::new);
        Player tmp = this.playRepo.save(player);
        category.getPlayers().add(tmp);
        return this.mapToDto(this.repo.saveAndFlush(category));
    }
}
