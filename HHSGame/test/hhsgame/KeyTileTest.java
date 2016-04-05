/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhsgame;

import java.awt.Graphics;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author RLH
 */
public class KeyTileTest {
    
    private static GameCharacter character;
    private KeyTile tile;
    
    public KeyTileTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        character = new GameCharacter(new EmptyTile(new BoardCoordinate(1,1)));
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        tile = new KeyTile(new BoardCoordinate(4,6), new Key(2));
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isPassable method, of class KeyTile.
     */
    @Test
    public void testIsPassable() {
        assertTrue("keyTile shoud be passable", tile.isPassable(character));
    }

    /**
     * Test of getKey method, of class KeyTile.
     */
    @Test
    public void testGetKey() {
        assertEquals("this should return key(2)", tile.getKey().getKeyCode(), 2);
    }

    /**
     * Test of getReplacement method, of class KeyTile.
     */
    @Test
    public void testGetReplacement() {
        Tile replacement = tile.getReplacement();
        assertTrue("keyTile should get replaced by an EmptyTile",replacement instanceof EmptyTile);
        assertEquals("EmptyTile location should be the same as KeyTile", 0, replacement.getPos().compareTo(tile.getPos()));
        
    }

    /**
     * Test of onCharacterEnter method, of class KeyTile.
     */
    @Test
    public void testOnCharacterEnter() {
        tile.onCharacterEnter(character);
        assertEquals("keyCode from tile should be the same as character.getKey()", tile.getKey().getKeyCode(), character.getKey().getKeyCode());
        
    }
    
}
