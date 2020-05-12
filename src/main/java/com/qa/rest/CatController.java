package com.qa.rest;

import com.qa.domain.Category;
import com.qa.domain.Player;
import com.qa.dto.CatDTO;
import com.qa.service.catService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class CatController {

    private final catService service;

    @Autowired
    public CatController(catService service) {
        this.service = service;
    }

    // mapping for reading all employees this is the bit you put after localhost...
    @GetMapping("/getAllCats")
    public ResponseEntity<List<CatDTO>> getAllCats(){
        return ResponseEntity.ok(this.service.readCat());
    }
    // creating is a POST method so post mapping and you take in a emp so requestBody but Dto version
    @PostMapping("/createCat")
    public ResponseEntity<CatDTO> createCat(@RequestBody Category category){
        return new ResponseEntity<CatDTO>(this.service.createCat(category), HttpStatus.CREATED);
    }
    // need to do find by ID and this will take a pathVariable rather than body having response entity because of Dto
    @GetMapping("/catById/{id}")
    public ResponseEntity<CatDTO> catById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.findCatById(id));
    }
    // update is to PUT so put mapping and again requestBody but two parameters again response entity
    @PutMapping("/updateCat/{id}")
    public ResponseEntity<CatDTO> updateCat(@PathVariable Long id, @RequestBody Category category){
        return ResponseEntity.ok(this.service.updateCat(id, category));
    }
    // update another version of updating again using Dto
    @PutMapping("/updateCat2")
    public ResponseEntity<CatDTO> updateCat2(@PathParam("id") Long id, @RequestBody Category category){
        return ResponseEntity.ok(this.service.updateCat(id, category));
    }
    // Deleting employee again takes a path variable of Long Id but using the the ? because boolean
    @DeleteMapping("/deleteCat/{id}")
    public ResponseEntity<?> deleteSect(@PathVariable Long id){
        return this.service.deleteCat(id)
                ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                : ResponseEntity.noContent().build();
    }

    @PatchMapping("/addPlayerToCat/{id}")
    public ResponseEntity<CatDTO> addEmpToSect(@PathVariable Long id, @RequestBody Player player){
        return new ResponseEntity<CatDTO>(this.service.addPlayerToCat(id,player), HttpStatus.ACCEPTED);
    }

}
