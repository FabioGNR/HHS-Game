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
public class GameBoardTest {
    
    GameBoard board = new GameBoard(800,800);
    private GameCharacter character;
    LevelReader reader;
    private BoardCoordinate charPos;
    
    public GameBoardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        reader = new LevelReader("level5.txt");
        board.loadLevel(reader, 0);
        character = board.getCharacter();

    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of togglePause method, of class GameBoard.
     */
    @Test
    public void testTogglePause() {
        
    }

    /**
     * Test of loadLevel method, of class GameBoard.
     */
    @Test
    public void testLoadLevel() {
        
    }

    /**
     * Test of reset method, of class GameBoard.
     */
    @Test
    public void testReset() {
        
    }

    /**
     * Test of paintComponent method, of class GameBoard.
     */
    @Test
    public void testPaintComponent() {}

    /**
     * Test of finishGame method, of class GameBoard.
     */
    @Test
    public void testFinishGame() {
        
    }

    /**
     * Test of moveCharacter method, of class GameBoard.
     */
    @Test
    public void testMoveCharacterToBarricade() {
        //barricade with right key 
        character.setKey(new Key(4));
        board.moveCharacter(Game.MoveDirection.Left);
        BoardCoordinate newPos = new BoardCoordinate(6,8);
        charPos = character.getCurrentTile().getPos();
        assertEquals("expected: character opens barricade", 0, newPos.compareTo(charPos));
        
        //barricade with wrong key
        board.moveCharacter(Game.MoveDirection.Left);
        BoardCoordinate expectedPos = new BoardCoordinate (6,8);
        assertEquals("expected: character has same position", 0, expectedPos.compareTo(charPos));
    }
        
        @Test
        public void testMoveCharacterToWall(){
            //test character doesn't bust through the wall
        board.moveCharacter(Game.MoveDirection.Up);
        BoardCoordinate newPos = new BoardCoordinate(7,8);
        charPos = character.getCurrentTile().getPos();
        assertEquals("expected: character can't go up", 0, newPos.compareTo(charPos));
    }
        
        @Test
        public void testMoveCharacterOutOfGameEdge(){
            //test character doesn't go further than (7,9)
            board.moveCharacter(Game.MoveDirection.Down);
            board.moveCharacter(Game.MoveDirection.Down);
            BoardCoordinate expectedPos = new BoardCoordinate(7,9);
            charPos = character.getCurrentTile().getPos();
        assertEquals("expected: character stay within gameboard", 0, expectedPos.compareTo(charPos));
        
        //test character doesn't go further than (9,9)
        character.setKey(new Key(4));
        board.moveCharacter(Game.MoveDirection.Right);
                board.moveCharacter(Game.MoveDirection.Right);
        board.moveCharacter(Game.MoveDirection.Right);
        BoardCoordinate expectedNewPos = new BoardCoordinate(9,9);
        BoardCoordinate charNewPos = character.getCurrentTile().getPos();
        assertEquals("expected: character stays within gameboard", 0, expectedNewPos.compareTo(charNewPos));
        }

    /**
     * Test of getCharacter method, of class GameBoard.
     */
    @Test
    public void testGetCharacter() {
        
    }
    
}
