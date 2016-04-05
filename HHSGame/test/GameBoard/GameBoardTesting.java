package GameBoard;

import static hhsgame.Game.*;
import hhsgame.*;
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

    static GameBoard board;
    static Map<BoardCoordinate, Tile> level;
    static Level buildLevel;
    static GameCharacter character;
    static LevelReader reader;

    public GameBoardTesting() {

    }

    @BeforeClass
    public static void setUp() {
        LevelReader levelReader = new LevelReader("testLevel.txt");
        board.loadLevel(levelReader, 0);
//        level = board.getLevelLayout();
        character = board.getCharacter();
        character.getCurrentTile();

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testPause() {
        board.togglePause();
        assertTrue("true", board.isPaused());
    }

    @Test
    public void loadLevelTest() {
//        setUp();
        LevelReader levelReader = new LevelReader("testLevel.txt");

        reader = new LevelReader("testlevel.txt");
        board.loadLevel(reader, 1);
        assertFalse("false", levelReader.equals(reader));
//        assertEquals(true, levelReader.equals(reader));

    }

    @Test
    public void resetTest() {

    }

    @Test
    public void levelSizeTest() {
        assertEquals(level.size(), 100);
    }

    @Test
    public void kindOfTileTest() {
        assertTrue(level.get(new BoardCoordinate(0, 0)) instanceof EmptyTile);
        assertTrue(level.get(new BoardCoordinate(1, 0)) instanceof KeyTile);
        assertTrue(level.get(new BoardCoordinate(1, 1)) instanceof Barricade);
        assertTrue(level.get(new BoardCoordinate(2, 4)) instanceof Wall);
        assertTrue(level.get(new BoardCoordinate(0, 4)) instanceof Finish);
    }

//    @Test
//    public void pickUpKeyTest() {
//        Tile tile = level.get(new BoardCoordinate(1,0));
//        KeyTile keyTile = (KeyTile)tile;
//        keyTile.onCharacterEnter(character);
//        checkKey(4);
//        character.setKey(null);
//    }
    @Test
    public void movementTest() {
        board.moveCharacterTest(MoveDirection.Right);
        checkKey(4);
        bustThroughBarricade(MoveDirection.Down);
        board.moveCharacterTest(MoveDirection.Down);
        checkKey(5);
        bustThroughBarricade(MoveDirection.Down);
        board.moveCharacterTest(MoveDirection.Down);
        checkKey(3);
    }

    private void checkKey(int keyCode) {
        assertTrue(character.hasKey());
        assertEquals(character.getKey().getKeyCode(), keyCode);
    }

    private void bustThroughBarricade(MoveDirection dir) {
        BoardCoordinate previous = character.getCurrentTile().getPos();
        board.moveCharacterTest(dir);
        BoardCoordinate next = character.getCurrentTile().getPos();
        assertFalse(previous.equals(next));
    }

    @Test
    public void wrongBarricadeTest() {
        BoardCoordinate previous = character.getCurrentTile().getPos();
        board.moveCharacterTest(MoveDirection.Down);
        BoardCoordinate next = character.getCurrentTile().getPos();
        assertTrue(previous.equals(next));
    }

    @Test
    public void finishingTest() {

    }
}
