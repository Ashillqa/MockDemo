package com.qa.service;


import com.qa.domain.Category;
import com.qa.domain.Player;
import com.qa.dto.CatDTO;
import com.qa.exception.CatNotFoundException;
import com.qa.exception.PlayerNotFoundException;
import com.qa.repo.catRepository;
import com.qa.repo.playerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
public class CatServiceUnitTest {
    @InjectMocks
    private catService service;
    @Mock
    private catRepository repo;
    @Mock
    private playerRepository prepo;
    @Mock
    private ModelMapper mapper;

    private List<Category> catList; // we need a list as this is involved in the methods and part of tests

    private Category testCat; // this will be your employee variable

    private Long id = 1L; // the test version wont have an id therefore we need to create our own

    private Category testCatWithId;  // this will be the employee with ID

    private CatDTO catDTO; // remember our results get converted into Dto so will do this as the expected

    private CatDTO mapToDto(Category cat){             // this what allows us to convert to Dto object
        return this.mapper.map(cat, CatDTO.class);
    }

    @Before  // this is how we set up our test and set up some situation
    public void setUp(){
        this.catList = new ArrayList<>(); // new empty list for when we look at CRUD methods
        this.testCat = new Category("good"); //setting a employee
        this.catList.add(testCat); // add this to the list
        this.testCatWithId = new Category(testCat.getLevel());
        // apply those details to one which we will give an ID to
        this.testCatWithId.setId(id); // give the id we created to the test employee with an id
        this.catDTO=this.mapToDto(testCatWithId); // remember the employee needs to be DTO
    }

    @Test
    public void getAllEmpTest(){
        when(repo.findAll()).thenReturn(this.catList); //list is returned when findall method called on repo
        when(this.mapper.map(testCatWithId, CatDTO.class)).thenReturn(catDTO); //test emp becomes Dto
        assertFalse("Service returned none", this.service.readCat().isEmpty()); //list not empty
        verify(repo,times(1)).findAll(); //repo findAll is called in repo
    }

    @Test
    public void createEmpTest(){
        when(repo.save(testCat)).thenReturn(testCatWithId); //when you save it creates test emp with ID
        when(this.mapper.map(testCatWithId, CatDTO.class)).thenReturn(catDTO); //testempID becomes DTO
        assertEquals(this.service.createCat(testCat),this.catDTO); //creating test emp results in empDto therefore
        verify(repo,times(1)).save(testCat); //save called once on testEmp
    }

    @Test
    public void findEmpById(){
        when(repo.findById(id)).thenReturn(java.util.Optional.ofNullable(testCatWithId)); //should get empWithID
        when(this.mapper.map(testCatWithId, CatDTO.class)).thenReturn(catDTO); //testempid to DTO
        assertEquals(this.service.findCatById(this.id),catDTO); //therefore returns the empDto
        verify(repo,times(1)).findById(id); //findbyId called only once
    }

    @Test
    public void deletePlayerExistingID(){
        when(this.repo.existsById(id)).thenReturn(true,false); //could be true or false
        assertFalse(service.deleteCat(id)); //it checks for it after so should be false
        verify(repo,times(1)).deleteById(id); //delete called once
        verify(repo, times(2)).existsById(id); // checks start and after so twice
    }

    @Test(expected = CatNotFoundException.class) // wont work on line 92 hence we expect this
    public void deleteNonExistingId(){
        when(this.repo.existsById(id)).thenReturn(false); //doesnt exist
        service.deleteCat(id); // call the id
        verify(repo, times(1)).existsById(id); // use this once
    }
    @Test
    public void addPlayerToCatTest(){
        Player player = new Player("Tom",4L,5L,6L);
        Player playerWithID = new Player(player.getName(),player.getGoals(),player.getAssists(),player.getTackles());
        playerWithID.setId(1L);
        testCatWithId.getPlayers().add(playerWithID);
        when(repo.findById(id)).thenReturn(java.util.Optional.ofNullable(testCatWithId));
        when(this.mapper.map(testCatWithId, CatDTO.class)).thenReturn(catDTO);
        assertEquals(this.service.addPlayerToCat(id,playerWithID),this.catDTO);
        verify(repo,times(1)).saveAndFlush(testCatWithId);




    }
}
