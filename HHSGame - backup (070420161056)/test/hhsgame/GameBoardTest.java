package hhsgame;

import org.junit.Before;
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
        BoardCoordinate expectedPos;
        //barricade with right key 
        character.setKey(new Key(4));
        board.moveCharacter(Game.MoveDirection.Left);
        expectedPos = new BoardCoordinate(6,8);
        charPos = character.getCurrentTile().getPos();
        assertEquals("expected: character opens barricade", 0, expectedPos.compareTo(charPos));
        
        //barricade with wrong key
        character.setKey(new Key(5));
        board.moveCharacter(Game.MoveDirection.Left);
        charPos = character.getCurrentTile().getPos();
        expectedPos = new BoardCoordinate (6,8);
        assertEquals("expected: character has same position", 0, expectedPos.compareTo(charPos));
        
        //barricade without key
        character.setKey(null);
        board.moveCharacter(Game.MoveDirection.Left);
        charPos = character.getCurrentTile().getPos();
        expectedPos = new BoardCoordinate (6,8);
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
        BoardCoordinate expectedPos;
        //test character doesn't go further than (9,9)
        expectedPos = new BoardCoordinate(9,9);
        character.setCurrentTile(board.getLevelLayout().get(expectedPos));
        board.moveCharacter(Game.MoveDirection.Down);
        charPos = character.getCurrentTile().getPos();
        assertEquals("expected: character stay within gameboard ( bottom edge )", 0, expectedPos.compareTo(charPos));       
        //test character doesn't go further than (9,9)
        expectedPos = new BoardCoordinate(9,9);
        character.setCurrentTile(board.getLevelLayout().get(expectedPos));
        board.moveCharacter(Game.MoveDirection.Right);
        assertEquals("expected: character stays within gameboard ( right edge )", 0, expectedPos.compareTo(charPos));
        // test character doesn't go lower than (0,0)
        expectedPos = new BoardCoordinate(0,0);
        character.setCurrentTile(board.getLevelLayout().get(expectedPos));
        charPos = character.getCurrentTile().getPos();
        board.moveCharacter(Game.MoveDirection.Up);
        assertEquals("expected: character stays within gameboard ( top edge )", 0, expectedPos.compareTo(charPos));
        // test character doesn't go left of (0,0)
        expectedPos = new BoardCoordinate(0,0);
        character.setCurrentTile(board.getLevelLayout().get(expectedPos));
        charPos = character.getCurrentTile().getPos();
        board.moveCharacter(Game.MoveDirection.Left);
        assertEquals("expected: character stays within gameboard ( left edge )", 0, expectedPos.compareTo(charPos));
    }
}
