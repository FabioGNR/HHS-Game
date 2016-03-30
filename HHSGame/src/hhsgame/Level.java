
package hhsgame;

import static hhsgame.Game.*;
import java.util.Map;
import java.util.TreeMap;

public class Level {

    String[][] tileBits = new String[ROWS][COLS];
    BoardCoordinate start;
    
    public Level(String[][] tileBits){
        this.tileBits = tileBits;
    }
    
    public Map<BoardCoordinate, Tile> buildLevel(){
        Map<BoardCoordinate, Tile> levelLayout = new TreeMap<>();
        outerloop:
        for(int y = 0; y < tileBits.length; y++){
            for(int x = 0; x < tileBits[y].length; x++){
                String currentBit = tileBits[y][x];
                Tile currentTile = new EmptyTile(new BoardCoordinate(x,y));
                String[] parts;
                parts = currentBit.split(" +");
                if(parts[0].equals("E")) {
                    if(parts.length == 1 || (parts.length == 2 && parts[1].equals("C"))) {
                        if(parts.length > 1) {
                            if(parts[1].equals("C")) {
                                start = new BoardCoordinate(x, y);
                            }
                        }
                    }
                } else if(parts[0].equals("K")) {
                    if(parts.length == 2 && isInt(parts[1])){
                        currentTile = new KeyTile(new BoardCoordinate(x, y), new Key(Integer.parseInt(parts[1])));
                    } 
                } else if(parts[0].equals("B")){
                    if(parts.length == 2 && isInt(parts[1])){
                        currentTile = new Barricade(new BoardCoordinate(x,y), Integer.parseInt(parts[1]));
                    }
                }else if(parts[0].equals("W")){
                    if(parts.length == 1){
                        currentTile = new Wall(new BoardCoordinate(x,y));
                    }
                } else if(parts[0].equals("F")){
                    if(parts.length == 1){
                        currentTile = new Finish(new BoardCoordinate(x,y));
                    }
                } else {
                    System.out.println("parts[0]: " + parts[0] + ".");
                    debugPrint(x, y);
                }
                levelLayout.put(currentTile.getPos(), currentTile);
            }
        }
        return levelLayout;
    }
    
    public void debugPrint(int xPos, int yPos) {
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
    
    private boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
