/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhsgame;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static hhsgame.Game.*;

/**
 *
 * @author RLH
 */
public class BoardCoordinateTest {
    
    public BoardCoordinateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getX method, of class BoardCoordinate.
     */
    @Test
    public void testGetX() {
        BoardCoordinate coordinate = new BoardCoordinate(5, 4);
        assertEquals("expected getX() is 5", coordinate.getX(), 5);
        
    }

    /**
     * Test of getY method, of class BoardCoordinate.
     */
    @Test
    public void testGetY() {
        BoardCoordinate coordinate = new BoardCoordinate(4,5);
        assertEquals("expected getY() is 5", coordinate.getY(), 5);
    }

    /**
     * Test of getLeft method, of class BoardCoordinate.
     */
    @Test
    public void testGetLeft() {
        BoardCoordinate coordinate = new BoardCoordinate(5, 6);
        BoardCoordinate coordinate2 = new BoardCoordinate(4, 6);
        assertEquals("expected: Coordinate.getLeft = coordinate2",0, coordinate.getLeft().compareTo(coordinate2));
        //        assertEquals("getLeft is not 4", coordinate.getLeft().getX(), 4);
        BoardCoordinate coordinate3 = new BoardCoordinate(0,6);
        assertEquals("expected: Coordinate3.getLeft(), x = 0", 0, coordinate3.compareTo(coordinate3.getLeft()));

    }

    /**
     * Test of getRight method, of class BoardCoordinate.
     */
    @Test
    public void testGetRight() {
        BoardCoordinate coordinate = new BoardCoordinate(3,4);
        BoardCoordinate coordinate2 = new BoardCoordinate(4,4);
        assertEquals("expected: coordinate.getRight() = coordinate2", 0, coordinate.getRight().compareTo(coordinate2));
        //assertEquals("x value of coordinate.getRight is 4", coordinate.getRight().getX(),4);
        BoardCoordinate coordinate3 = new BoardCoordinate(COLS-1,4);
        assertEquals("expected: coordinate3.getRight(), x = COLS-1", 0, coordinate3.compareTo(coordinate3.getRight()));
        
    }

    /**
     * Test of getUp method, of class BoardCoordinate.
     */
    @Test
    public void testGetUp() {
        BoardCoordinate coordinate = new BoardCoordinate(3,4);
        BoardCoordinate coordinate2 = new BoardCoordinate(3,5);
        assertEquals("expected: coordinate2.getUp() = coordinate", 0, coordinate2.getUp().compareTo(coordinate));
        //assertEquals("value y of coordinate.getUp().getY() is 3", coordinate.getUp().getY(), 3);
        BoardCoordinate coordinate3 = new BoardCoordinate(3,0);
        assertEquals("expected: coordinate3.getUp(), y = 0", 0, coordinate3.compareTo(coordinate3.getUp()));
    }

    /**
     * Test of getDown method, of class BoardCoordinate.
     */
    @Test
    public void testGetDown() {
        BoardCoordinate coordinate = new BoardCoordinate(5,7);
        BoardCoordinate coordinate2 = new BoardCoordinate(5,6);
        assertEquals("expected: coordinate2.getDown() = coordinate", 0, coordinate2.getDown().compareTo(coordinate));
        //assertEquals("", 0, coordinate2.getDown().getY(),7);
        BoardCoordinate coordinate3 = new BoardCoordinate(5, ROWS-1);
        assertEquals("expected: coordinate3.getDown = ROWS-1", 0, coordinate3.compareTo(coordinate3.getDown()));
    }

    /**
     * Test of getScreenX method, of class BoardCoordinate.
     */
    @Test
    public void testGetScreenX() {
        
    }

    /**
     * Test of getScreenY method, of class BoardCoordinate.
     */
    @Test
    public void testGetScreenY() {
        
    }

    /**
     * Test of compareTo method, of class BoardCoordinate.
     */
    @Test
    public void testCompareTo() {
        BoardCoordinate coordinate = new BoardCoordinate(4,3);
        assertEquals("coordinate should be a 0", 0, coordinate.compareTo(coordinate));
        BoardCoordinate coordinate2 = new BoardCoordinate(3,4);
        assertTrue("coordinate2.compareTo(coordinate) is greater than 0", coordinate2.compareTo(coordinate) > 0);
        assertTrue("coordinate.compareTo(coordinate2) is less than 0", coordinate.compareTo(coordinate2) < 0);
    }
    
}
