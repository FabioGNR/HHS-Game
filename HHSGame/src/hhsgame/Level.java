
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
        Map levelLayout = new TreeMap<BoardCoordinate, Tile>();
        outerloop:
        for(int y = 0; y < tileBits.length; y++){
            for(int x = 0; x < tileBits[y].length; x++){
                String currentBit = tileBits[y][x];
                Tile currentTile = new EmptyTile(new BoardCoordinate(x,y));
                String[] parts;
                parts = currentBit.split(" +");
                if(parts[0] == "E") {
                    if(parts.length == 1 || (parts.length == 2 && parts[1] == "C")) {
                        if(parts.length > 1) {
                            if(parts[1] == "C") {
                                start = new BoardCoordinate(y, x);
                            }
                        }
                    }
                } else if(parts[0] == "K") {
                    if(parts.length == 2 && isInt(parts[1])){
                        currentTile = new KeyTile(new BoardCoordinate(x, y), new Key(Integer.parseInt(parts[1])));
                    } 
                } else if(parts[0] == "B"){
                    if(parts.length == 2 && isInt(parts[1])){
                        currentTile = new Barricade(new BoardCoordinate(x,y), Integer.parseInt(parts[1]));
                    }
                }else if(parts[0] == "W"){
                    if(parts.length == 1){
                        currentTile = new Wall(new BoardCoordinate(x,y));
                    }
                } else if(parts[0] == "F"){
                    if(parts.length == 1){
                        currentTile = new Finish(new BoardCoordinate(x,y));
                    }
                } else {
                    debugPrint(x, y);
                }
                levelLayout.put(currentTile.getPos(), currentTile);
            }
        }
        return levelLayout;
    }
    
    public void debugPrint(int xPos, int yPos) {
        for(int y = 0; y <= yPos; y++) {
            for(int i = 0; i < tileBits[y].length; i++) {
                System.out.print(tileBits[y][i]);
            }
            System.out.println("");
        }
        for(int x = 0; x <= xPos; x++) {
            System.out.println(tileBits[yPos][x]);
        }
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
