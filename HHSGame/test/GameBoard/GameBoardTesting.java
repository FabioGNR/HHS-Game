/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameBoard;

import hhsgame.Barricade;
import hhsgame.BoardCoordinate;
import hhsgame.EmptyTile;
import hhsgame.Finish;
import hhsgame.GameBoard;
import hhsgame.GameCharacter;
import hhsgame.KeyTile;
import hhsgame.LevelReader;
import hhsgame.Tile;
import hhsgame.Wall;
import java.util.Map;
import java.util.TreeMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mayra Westerik
 */
public class GameBoardTesting {
    
    GameBoard board = new GameBoard(800, 800);
    Map<BoardCoordinate, Tile> level;
    GameCharacter character;
    
    public GameBoardTesting() {
        
    }
    
    @Before
    public void setUp() {
        LevelReader reader = new LevelReader("testLevel.txt");
        board.loadLevel(reader, 0);
        level = board.getLevelLayout();
        character = board.getCharacter();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testPause() {
        assertFalse(board.isPaused());
        board.togglePause();
        assertTrue(board.isPaused());
    }
    
    @Test
    public void levelSizeTest() {
        assertEquals(level.size(), 100);
    }
    
    @Test
    public void kindOfTileTest() {
        assertTrue(level.get(new BoardCoordinate(0,0)) instanceof EmptyTile);
        assertTrue(level.get(new BoardCoordinate(1,0)) instanceof KeyTile);
        assertTrue(level.get(new BoardCoordinate(1,1)) instanceof Barricade);
        assertTrue(level.get(new BoardCoordinate(2,4)) instanceof Wall);
        assertTrue(level.get(new BoardCoordinate(0,4)) instanceof Finish);
    }
    
    @Test
    public void pickUpKeyTest() {
        Tile tile = level.get(new BoardCoordinate(1,0));
        KeyTile keyTile = (KeyTile)tile;
        keyTile.onCharacterEnter(character);
        assertEquals(character.getKey().getKeyCode(), 4);
    }
}
