
package hhsgame;

import static hhsgame.Game.*;
import java.util.Map;
import java.util.TreeMap;

public class Level {

    private String[][] tileBits = new String[ROWS][COLS];
    private final String filename;
    private Map<BoardCoordinate, Tile> levelLayout = null;
    private BoardCoordinate start;
    
    // initalize class with the Strings representing tiles 
    // and the level's filename
    public Level(String[][] tileBits, String filename){
        this.tileBits = tileBits;
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
    
    public BoardCoordinate getStart() {
        return start;
    }
    
    // returns a clone of the Map with Tiles, calling buildLevel if required
    // returns a clone because we don't want our Map to be altered
    public Map<BoardCoordinate, Tile> getLevelLayout() {
        if(levelLayout == null) {
            buildLevel();
        }
        return new TreeMap<>(levelLayout);
    }
    
    // go through each TileBit and construct Tiles
    private void buildLevel(){
        levelLayout = new TreeMap<>();
        for(int y = 0; y < tileBits.length; y++){
            for(int x = 0; x < tileBits[y].length; x++){
                TileBit currentBit = new TileBit(tileBits[y][x]);
                Tile currentTile = new EmptyTile(new BoardCoordinate(x,y));
                if(currentBit.getStart()) {
                    start = new BoardCoordinate(x, y);
                } else if(currentBit.getType() == 'K') {
                    currentTile = new KeyTile(new BoardCoordinate(x, y), new Key(currentBit.getKeyCode())); 
                } else if(currentBit.getType() == 'B'){
                    currentTile = new Barricade(new BoardCoordinate(x,y), currentBit.getKeyCode());
                } else if(currentBit.getType() == 'W'){
                    currentTile = new Wall(new BoardCoordinate(x,y));
                } else if(currentBit.getType() == 'F'){
                    currentTile = new Finish(new BoardCoordinate(x,y));
                } else if(currentBit.getType() != 'E') {
                    System.out.println("Attempted Tile type not recognized: " + currentBit.getType());
                    debugPrint(x, y);
                }
                // place new tile in a Map
                levelLayout.put(currentTile.getPos(), currentTile);
            }
        }
    }
    
    private void debugPrint(int xPos, int yPos) {
        System.out.println("Debug:");
        for(int y = 0; y < yPos; y++) {
            for(int i = 0; i < tileBits[y].length; i++) {
                System.out.print(tileBits[y][i]);
            }
            System.out.println("");
        }
        for(int x = 0; x <= xPos; x++) {
            System.out.print(tileBits[yPos][x]);
        }
        System.out.println("");
    }

}
