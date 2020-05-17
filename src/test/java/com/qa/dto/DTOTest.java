package com.qa.dto;

import com.qa.domain.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class DTOTest {

    private PlayerDTO pDTO;
    private PlayerDTO oDTO;

    private CatDTO catDTO;
    private CatDTO cat2DTO;

    private UserDTO userDTO;
    private UserDTO user2DTO;

    @Before
    public void setUp() {
        pDTO = new PlayerDTO("harry",6L,4L,2L);
        oDTO = new PlayerDTO("harry",6L,4L,2L);

        catDTO = new CatDTO("good");
        cat2DTO = new CatDTO("good");

        userDTO = new UserDTO("USER1","Password1");
        user2DTO = new UserDTO("USER1","Password1");
    }

    ////////////////////////PLAYER DTO////////////////////////////////////////
    @Test(expected = NullPointerException.class)
    public void createPDTOWithId() {
        assertEquals(1, pDTO.getId(), 0);
    }

    @Test
    public void PDTONullButOtherNameNotNull() {
        pDTO.setName("steve");
        assertFalse(pDTO.getName().equals(oDTO.getName()));
    }
    @Test
    public void PDTOAssists(){
        pDTO.setAssists(3L);
        assertFalse(pDTO.getAssists().equals(oDTO.getAssists()));
    }
    @Test
    public void PDTOTackles(){
        pDTO.setTackles(1L);
        assertFalse(pDTO.getTackles().equals(oDTO.getTackles()));
    }

    @Test
    public void PDTOIdNullButOtherCatNotNull() {
        pDTO.setId(5L);
        assertEquals(false,pDTO.getId().equals(oDTO.getId()));
    }

    @Test
    public void PDTONullButotherGoalsNotNull() {
        pDTO.setGoals(4L);
        oDTO.setGoals(1L);
        assertFalse(pDTO.getGoals().equals(oDTO.getGoals()));
    }
    @Test(expected = NullPointerException.class)
    public void eConstructorPDTOTest(){
        PlayerDTO test1 = new PlayerDTO();
        PlayerDTO test2 = new PlayerDTO();
        assertTrue(test1.equals(test2));
    }

    @Test
    public void PlayerDTOequalsWithNull() {
        assertFalse(pDTO.equals(null));
    }
    ////////////////////////////////CATDTO/////////////////////////////////////

    @Test
    public void CatDTOhashCodeTest() {
        assertEquals(catDTO.hashCode(), cat2DTO.hashCode());
    }
    @Test
    public void eConstructorCATDTOTest(){
        CatDTO test1 = new CatDTO();
        CatDTO test2 = new CatDTO();
        assertTrue(test1.equals(test2));
    }
    @Test(expected = NullPointerException.class)
    public void CatDTOWithId() {
        assertEquals(1, catDTO.getId(), 0);
    }

    @Test
    public void CatDTONullButOtherNameNotNull() {
        catDTO.setLevel("bad");
        assertFalse(catDTO.getLevel().equals(cat2DTO.getLevel()));
    }

    @Test
    public void CatDTOIdNullButOtherCatNotNull() {
        catDTO.setId(5L);
        assertEquals(false,catDTO.getId().equals(cat2DTO.getId()));
    }

    @Test(expected = NullPointerException.class)
    public void CatDTOPlayerNullButOtherNotNull() {
        List<PlayerDTO> players = new ArrayList<>();
        players.add(pDTO);
        catDTO.setPlayer(null);
        cat2DTO.setPlayer(players);

        assertEquals(false,catDTO.getPlayer().equals(cat2DTO.getPlayer()));
    }

    @Test
    public void CATLEVELeNullButOtherNameNotNull() {
        catDTO.setLevel(null);
        assertFalse(cat2DTO.equals(catDTO));
    }
    @Test
    public void CATPLAYERNullButOtherNameNotNulNl() {
        catDTO.setPlayer(null);
        assertEquals(false,catDTO.equals(cat2DTO));
    }
    @Test
    public void equalsWithDifferentObjectCatDTO() {

        assertFalse(catDTO.equals(new Object()));
    }

    /////////////////////USER DTO/////////////////////////////////////////////

    @Test
    public void userDTOhashCodeTest() {
        assertEquals(userDTO.hashCode(), user2DTO.hashCode());
    }
    @Test(expected = NullPointerException.class)
    public void eConstructorUSERDTOTest(){
        UserDTO test1 = new UserDTO();
        UserDTO test2 = new UserDTO();
        assertTrue(test1.equals(test2));
    }
    @Test
    public void createUserDTOWithId() {
        userDTO.setId(2L);
        assertEquals(2, userDTO.getId(), 0);
    }
    @Test
    public void userDTOusername(){
        userDTO.setUsername("User2");
        user2DTO.setUsername("User3");
        assertEquals(false,userDTO.getUsername().equals(user2DTO.getUsername()));
    }
    @Test
    public void userDTOPasswordTest(){
        userDTO.setPassword("Password");
        user2DTO.setPassword("Password2");
        assertEquals(false,userDTO.getPassword().equals(user2DTO.getPassword()));

    }





































}
