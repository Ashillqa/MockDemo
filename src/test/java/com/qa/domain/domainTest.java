package com.qa.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class domainTest {
    private Player player;
    private Player other;

    private Category cat;
    private Category cat2;

    private User user;
    private User user2;

    @Before
    public void setUp() {
        player = new Player( "Chris", 6L,7L,8L);
        other = new Player( "Chris", 6L,7L,8L);

        cat = new Category("good");
        cat2 = new Category("good");

        user = new User("Harry01","Pass1");
        user2 = new User("Harry01","Pass1");
    }

    @Test
    public void EmptyPlayer(){
        Player one = new Player();
        Player two = new Player();
        assertEquals(one,two);
    }

    @Test
    public void equalsWithNull() {
        assertFalse(player.equals(null));
    }
    @Test
    public void equalsWithDifferentObject() {
        assertFalse(player.equals(new Object()));
    }
    @Test(expected = NullPointerException.class)
    public void createPlayerWithId() {
        assertEquals(1, player.getId(), 0);
    }
    @Test
    public void PlayerAssEquals(){ assertEquals(7,player.getAssists(),0);}
    @Test
    public void checkEquality() {
        assertTrue(player.equals(player));
    }
    @Test
    public void checkEqualityBetweenDifferentObjects() {
        assertEquals(player.equals(other),false);
    }

    @Test
    public void playerNameNullButOtherNameNotNull() {
        player.setGoals(null);
        assertFalse(player.equals(other));
    }
    @Test
    public void playerNameNullButOtherNameNotNulNl() {
        player.setName("one");
        other.setName("two");
        assertEquals(false,player.equals(other));
    }
    @Test
    public void playerNameNullButOtherNameNotNulli() {
        player.setId(null);
        assertFalse(player.equals(other));
    }
    @Test(expected = NullPointerException.class)
    public void PlayerAssistsNull(){
        player.setAssists(null);
        assertFalse(player.getAssists().equals(other.getAssists()));
    }
    @Test
    public void playerTackles(){
        player.setTackles(9L);
        assertFalse(player.getTackles().equals(other.getTackles()));
    }

    @Test
    public void hashCodeTest() {
        assertEquals(player.hashCode(), other.hashCode());
    }

    @Test
    public void hashCodeTestWithNull() {
        Player p = new Player(null,null,null,null);
        Player o = new Player(null,null,null,null);
        assertEquals(p.hashCode(), o.hashCode());
    }
    ////////////////////////CATEGORY/////////////////////////////////////////////

   @Test
   public void EmptyCat(){
        Category one = new Category();
        Category two = new Category();
        assertEquals(false,one.equals(two));
   }


    @Test(expected = NullPointerException.class)
    public void createCatWithId() {
        assertEquals(1, cat.getId(), 0);
    }

    @Test
    public void CatDivNullButOtherNameNotNull() {
        cat.setLevel("average");
        assertFalse(cat.getLevel().equals(cat2.getLevel()));
    }

    @Test(expected = NullPointerException.class)
    public void CatPlayerNullButOtherCatNotNull() {
        List<Player> players = new ArrayList<>();
        players.add(player);
        cat.setPlayers(null);
        cat2.setPlayers(players);

        assertEquals(false,cat.getPlayers().equals(cat2.getPlayers()));
    }
    @Test
    public void CatIdNullButOtherCatNotNull() {
        cat.setId(5L);
        assertEquals(false,cat.getId().equals(cat2.getId()));
    }

    //////////////////////USER//////////////////////////////////////////////////

    @Test
    public void UserEmpty(){
        User use = new User();
        User use2 = new User();
        assertEquals(use,use2);
    }


    @Test(expected = NullPointerException.class)
    public void createUserWithId() {
        assertEquals(1, user.getId(), 0);
    }

    @Test
    public void UserDivNullButOtherNameNotNull() {
        user.setUsername("NOBODY");
        assertFalse(user.getUsername().equals(user2.getUsername()));
    }

    @Test
    public void UserNullButOtherUSERNotNull() {
        user.setPassword("Different101");

        assertEquals(false,user.getPassword().equals(user2.getPassword()));
    }
    @Test(expected = NullPointerException.class)
    public void UserIdNullButOtherCatNotNull() {
        user.setId(5L);
        assertEquals(false,user2.getId().equals(user.getId()));
    }
    @Test
    public void UserequalsWithNull() {
        assertFalse(user.equals(null));
    }

    @Test
    public void UserequalsWithDifferentObject() {
        assertFalse(user.equals(new Object()));
    }
    @Test
    public void UsercheckEquality() {
        assertTrue(user.equals(user));
    }
    @Test
    public void UsercheckEqualityBetweenDifferentObjects() {
        assertEquals(user.equals(user2),true);
    }
    @Test
    public void UserhashCodeTest() {
        assertEquals(user.hashCode(), user2.hashCode());
    }

























}

