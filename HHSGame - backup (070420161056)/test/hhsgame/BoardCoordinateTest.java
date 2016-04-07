/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhsgame;

import org.junit.Test;
import static org.junit.Assert.*;
import static hhsgame.Game.*;

/**
 *
 * @author RLH
 */
public class BoardCoordinateTest {

    /**
     * Test of getX method, of class BoardCoordinate.
     */
    @Test
    public void testGetX() {
        //test coordinate.getX() return the right value x of coordinate
        BoardCoordinate coordinate = new BoardCoordinate(5, 4);
        assertEquals("expected getX() is 5", coordinate.getX(), 5);
    }

    /**
     * Test of getY method, of class BoardCoordinate.
     */
    @Test
    public void testGetY() {
        //test coordinate.getY() return the right value y of coordinate
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
        //
        assertEquals("expected: Coordinate.getLeft = coordinate2",0, coordinate.getLeft().compareTo(coordinate2));

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

        BoardCoordinate coordinate3 = new BoardCoordinate(5, ROWS-1);
        assertEquals("expected: coordinate3.getDown = ROWS-1", 0, coordinate3.compareTo(coordinate3.getDown()));
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
    
    @Test
    public void testConstructorLimit() {
        BoardCoordinate coordinate = new BoardCoordinate(-5, -3);
        assertEquals("coordinate with -5 as X in constructor should be adjusted to 0 as X", 0, coordinate.getX());
        assertEquals("coordinate with -3 as Y in constructor should be adjusted to 0 as Y", 0, coordinate.getY());
        coordinate = new BoardCoordinate(COLS+3, ROWS+4);
        assertEquals("coordinate with COLS+ as X in constructor should be adjusted to COLS-1 as X", COLS-1, coordinate.getX());
        assertEquals("coordinate with COLS+4 as Y in constructor should be adjusted to ROWS-1 as Y", ROWS-1, coordinate.getY());
    }
}
