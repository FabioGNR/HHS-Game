package hhsgame;

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
    
    @Before
    public void setUp() {
        reader = new LevelReader("testLevels.txt");
        board.loadLevel(reader.getLevels().get(0));
        character = board.getCharacter();
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
}
