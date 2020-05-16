package com.qa.rest;

import com.qa.domain.Player;
import com.qa.domain.User;
import com.qa.dto.PlayerDTO;
import com.qa.dto.UserDTO;
import com.qa.service.userService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerUnitTest {
    @InjectMocks
    private userController controller;
    @Mock
    private userService service;

    private List<User> users;
    private User testuser;
    private User testUserWithId;
    private User newUser;
    private User updateUser;
    private Long id = 1L;
    private UserDTO uDTO;
    private final ModelMapper mapper = new ModelMapper();
    private UserDTO mapToDto(User user){return this.mapper.map(user,UserDTO.class);}

    @Before
    public void setUp(){
        this.users = new ArrayList<>();
        this.testuser= new User("User1", "Password1");
        this.users.add(testuser);
        this.testUserWithId = new User(testuser.getUsername(),testuser.getPassword());
        this.testUserWithId.setId(this.id);
        this.uDTO = this.mapToDto(testUserWithId);

    }

    @Test
    public void getAllUsersTest(){
        when(service.readUser()).thenReturn(this.users.stream().map(this::mapToDto).collect(Collectors.toList()));
        assertFalse("No emps found", this.controller.getAllUsers().getBody().isEmpty());
        verify(service, times(1)).readUser();
    }

    @Test
    public void createNoteTest(){
        when(this.service.createUser(testuser)).thenReturn(this.uDTO);
        assertEquals(this.controller.createUser(testuser), new ResponseEntity<UserDTO>(this.uDTO, HttpStatus.CREATED));
        verify(this.service, times(1)).createUser(testuser);
    }

    @Test
    public void deleteNoteTestFalse(){
        this.controller.deleteUser(id);
        verify(service, times(1)).deleteUser(id);
    }
    @Test
    public void deleteEmpTestTrue(){
        when(service.deleteUser(1L)).thenReturn(true);
        this.controller.deleteUser(1L);
        verify(service, times(1)).deleteUser(1L);
    }

    @Test
    public void getEmpByIDTest(){
        when(this.service.findUserById(id)).thenReturn(this.uDTO);
        assertEquals(this.controller.userById(id), new ResponseEntity<UserDTO>(this.uDTO, HttpStatus.OK));
        verify(service, times(1)).findUserById(id);
    }

    @Test
    public void updateTest(){
        when(this.service.updateUser(id,updateUser)).thenReturn(uDTO);
        assertEquals(this.controller.updateUser(id,updateUser),new ResponseEntity<UserDTO>(this.uDTO,HttpStatus.OK));
    }





}
