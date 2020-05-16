package com.qa.service;

import com.qa.domain.Player;
import com.qa.domain.User;
import com.qa.dto.PlayerDTO;
import com.qa.dto.UserDTO;
import com.qa.exception.PlayerNotFoundException;
import com.qa.exception.UserNotFoundException;
import com.qa.repo.playerRepository;
import com.qa.repo.userRepository;
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

@RunWith(SpringRunner.class)
public class UserServiceUnitTest {
    @InjectMocks
    private userService service;

    @Mock
    private userRepository repo;

    @Mock
    private ModelMapper mapper;

    private List<User> userList; // we need a list as this is involved in the methods and part of tests

    private User testUser; // this will be your employee variable

    private Long id = 1L; // the test version wont have an id therefore we need to create our own

    private User testUserWithId;  // this will be the employee with ID

    private UserDTO uDTO; // remember our results get converted into Dto so will do this as the expected

    private UserDTO mapToDto(User user){             // this what allows us to convert to Dto object
        return this.mapper.map(user, UserDTO.class);
    }
    @Before
    public void setUp(){
        this.userList = new ArrayList<>();
        this.testUser = new User("tom","Hello123"); //setting a employee
        this.userList.add(testUser); // add this to the list
        this.testUserWithId = new User(testUser.getUsername(),testUser.getPassword());
        // apply those details to one which we will give an ID to
        this.testUserWithId.setId(id);
        this.uDTO=this.mapToDto(testUserWithId);
    }
    @Test
    public void getAllEmpTest(){
        when(repo.findAll()).thenReturn(this.userList);
        when(this.mapper.map(testUserWithId, UserDTO.class)).thenReturn(uDTO);
        assertFalse("Service returned none", this.service.readUser().isEmpty()); //list not empty
        verify(repo,times(1)).findAll(); //repo findAll is called in repo
    }

    @Test
    public void createEmpTest(){
        when(repo.save(testUser)).thenReturn(testUserWithId); //when you save it creates test emp with ID
        when(this.mapper.map(testUserWithId, UserDTO.class)).thenReturn(uDTO); //testempID becomes DTO
        assertEquals(this.service.createUser(testUser),this.uDTO); //creating test emp results in empDto therefore
        verify(repo,times(1)).save(testUser); //save called once on testEmp
    }

    @Test
    public void findEmpById(){
        when(repo.findById(id)).thenReturn(java.util.Optional.ofNullable(testUserWithId)); //should get empWithID
        when(this.mapper.map(testUserWithId, UserDTO.class)).thenReturn(uDTO); //testempid to DTO
        assertEquals(this.service.findUserById(this.id),uDTO); //therefore returns the empDto
        verify(repo,times(1)).findById(id); //findbyId called only once
    }

    @Test
    public void deletePlayerExistingID(){
        when(this.repo.existsById(id)).thenReturn(true,false); //could be true or false
        assertFalse(service.deleteUser(id)); //it checks for it after so should be false
        verify(repo,times(1)).deleteById(id); //delete called once
        verify(repo, times(2)).existsById(id); // checks start and after so twice
    }

    @Test(expected = UserNotFoundException.class) // wont work on line 92 hence we expect this
    public void deleteNonExistingId() {
        when(this.repo.existsById(id)).thenReturn(false); //doesnt exist
        service.deleteUser(id); // call the id
        verify(repo, times(1)).existsById(id); // use this once
    }




}
