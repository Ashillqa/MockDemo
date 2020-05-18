package com.qa.service;

import com.qa.domain.Player;
import com.qa.dto.PlayerDTO;
import com.qa.exception.PlayerNotFoundException;
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
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
public class PlayerServiceUnitTest {
        @InjectMocks
        private playerService service; // This is what you are mocking and will be where dependencies are injected

        @Mock
        private playerRepository repo;  // this is one of the declarations and in the constructor
        @Mock
        private ModelMapper mapper; // same reason as above

        private List<Player> playerList; // we need a list as this is involved in the methods and part of tests

        private Player testPlayer; // this will be your employee variable

        private Long id = 1L; // the test version wont have an id therefore we need to create our own

        private Player testPlayerWithId;  // this will be the employee with ID

        private PlayerDTO pDTO; // remember our results get converted into Dto so will do this as the expected

        private PlayerDTO mapToDto(Player player){             // this what allows us to convert to Dto object
            return this.mapper.map(player, PlayerDTO.class);
        }

        @Before  // this is how we set up our test and set up some situation
        public void setUp(){
            this.playerList = new ArrayList<>(); // new empty list for when we look at CRUD methods
            this.testPlayer = new Player("tom",4L,5L,6L); //setting a employee
            this.playerList.add(testPlayer); // add this to the list
            this.testPlayerWithId = new Player(testPlayer.getName(),testPlayer.getGoals(),testPlayer.getAssists(),testPlayer.getTackles());
            // apply those details to one which we will give an ID to
            this.testPlayerWithId.setId(id); // give the id we created to the test employee with an id
            this.pDTO=this.mapToDto(testPlayerWithId); // remember the employee needs to be DTO
        }

        @Test
        public void getAllPlayerTest(){
            when(repo.findAll()).thenReturn(this.playerList); //list is returned when findall method called on repo
            when(this.mapper.map(testPlayerWithId, PlayerDTO.class)).thenReturn(pDTO); //test emp becomes Dto
            assertFalse("Service returned none", this.service.readPlayer().isEmpty()); //list not empty
            verify(repo,times(1)).findAll(); //repo findAll is called in repo
        }

        @Test
        public void createPlayerTest(){
            when(repo.save(testPlayer)).thenReturn(testPlayerWithId); //when you save it creates test emp with ID
            when(this.mapper.map(testPlayerWithId, PlayerDTO.class)).thenReturn(pDTO); //testempID becomes DTO
            assertEquals(this.service.createPlayer(testPlayer),this.pDTO); //creating test emp results in empDto therefore
            verify(repo,times(1)).save(testPlayer); //save called once on testEmp
        }

        @Test
        public void findPlayerById(){
            when(repo.findById(id)).thenReturn(java.util.Optional.ofNullable(testPlayerWithId)); //should get empWithID
            when(this.mapper.map(testPlayerWithId, PlayerDTO.class)).thenReturn(pDTO); //testempid to DTO
            assertEquals(this.service.findPlayerById(this.id),pDTO); //therefore returns the empDto
            verify(repo,times(1)).findById(id); //findbyId called only once
        }

        @Test
        public void deletePlayerExistingID(){
            when(this.repo.existsById(id)).thenReturn(true,false); //could be true or false
            assertFalse(service.deletePlayer(id)); //it checks for it after so should be false
            verify(repo,times(1)).deleteById(id); //delete called once
            verify(repo, times(2)).existsById(id); // checks start and after so twice
        }

        @Test(expected = PlayerNotFoundException.class) // wont work on line 92 hence we expect this
        public void deleteNonExistingId() {
            when(this.repo.existsById(id)).thenReturn(false); //doesnt exist
            service.deletePlayer(id); // call the id
            verify(repo, times(1)).existsById(id); // use this once
        }

    }

