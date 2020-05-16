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
        player = new Player( "Chris", 6L);
        other = new Player( "Chris", 6L);

        cat = new Category("good");
        cat2 = new Category("good");

        user = new User("Harry01","Pass1");
        user2 = new User("Harry01","Pass1");
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
        player.setName(null);
        assertEquals(false,player.equals(other));
    }
    @Test
    public void playerNameNullButOtherNameNotNulli() {
        player.setId(null);
        assertFalse(player.equals(other));
    }

    @Test
    public void hashCodeTest() {
        assertEquals(player.hashCode(), other.hashCode());
    }

    @Test
    public void hashCodeTestWithNull() {
        Player p = new Player(null,null);
        Player o = new Player(null,null);
        assertEquals(p.hashCode(), o.hashCode());
    }

    @Test
    public void test_SetterProperlyName() throws NoSuchFieldException,IllegalAccessException{
        final Player p1 = new Player();
        p1.setName("george");
        final Field feild = p1.getClass().getDeclaredField("name");
        feild.setAccessible(true);
        assertEquals("Fields didn't match",feild.get(p1),"george");
    }

    @Test
    public void testGetter_getsValueName() throws NoSuchFieldException, IllegalAccessException {
        final Player p2 = new Player();
        final Field field = p2.getClass().getDeclaredField("name");
        field.setAccessible(true);
        field.set(p2,"magic_values");

        final String result = p2.getName();
        assertEquals("field wasn't retrieved properly", result, "magic_values");
    }

    @Test
    public void testGetter_getsValueGoals() throws NoSuchFieldException, IllegalAccessException {
        final Player p3 = new Player();
        final Field field = p3.getClass().getDeclaredField("goals");
        field.setAccessible(true);
        field.set(p3,4L);

        final Long result = p3.getGoals();
        assertEquals(result,4L,0);
    }
    @Test
    public void test_SetterProperlyGoals() throws NoSuchFieldException,IllegalAccessException{
        final Player p4 = new Player();
        p4.setGoals(2L);
        final Field field= p4.getClass().getDeclaredField("goals");
        field.setAccessible(true);
        assertEquals("Fields didn't match",field.get(p4),2L);
    }

    @Test
    public void test_SetterProperlyCat() throws NoSuchFieldException,IllegalAccessException{
        final Player p5 = new Player();
        final Category C1 = new Category("good");
        p5.setCategory(C1);
        final Field field = p5.getClass().getDeclaredField("category");
        field.setAccessible(true);
        assertEquals("Fields didn't match",field.get(p5),C1);
    }

    @Test
    public void testGetter_getsValueCat() throws NoSuchFieldException, IllegalAccessException {
        final Player p6 = new Player();
        final Category C2 = new Category("bad");
        final Field field = p6.getClass().getDeclaredField("category");
        field.setAccessible(true);
        field.set(p6,C2);

        final Category result = p6.getCategory();
        assertEquals("field wasn't retrieved properly", result, C2);
    }
    ////////////////////////CATEGORY/////////////////////////////////////////////

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





















}

