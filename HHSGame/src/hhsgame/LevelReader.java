package hhsgame;

import java.io.*;
import java.util.*;
import static hhsgame.Game.*;


public class LevelReader {

    private BufferedReader in = null;
    private int levelAmount;
    private String fileName;
    
    
    public LevelReader(String fileName) {

        this.fileName = fileName;
        levelAmount = readLevelAmount();
    }
    
    private void openFile()
    {
        try {
            in = new BufferedReader(new FileReader(fileName));
        } catch(Exception e) {
            in = null;
            System.out.println("File was not found.");
            System.exit(0);
        }
    }
    
    private int readLevelAmount() {
        openFile();
//        if(in == null) {
//            return 0;
//        }
        int lineCount = 0;
        try {
            while(in.readLine() != null) {
                lineCount++;
            }
        } catch (Exception e) {
            return 0;
        }
        return (lineCount/10)+1;
    }
    
    public int getLevelAmount() {
        return levelAmount;
    }
    
    public Map<BoardCoordinate, Tile> getLevelTiles(int level) {
        openFile();
        Map<BoardCoordinate, Tile> tiles = new TreeMap();
        if(in != null) {
            try {
                in.reset();
                int lineIndex = 0;
                String line;
                while((line = in.readLine()) != null) {
                    if(lineIndex > (level*10)+10) {
                        break; // we're past the level
                    }
                    else if(lineIndex >= level*10) {
                        int y = lineIndex - level*10;
                        String[] items = line.split(",");
                        for(int x = 0; x < items.length && x < COLS; x++) {
                            Tile tile = null;
                            BoardCoordinate pos = new BoardCoordinate(x,y);
                            String[] parameters = items[x].split(" +");
                            if(parameters[0].equals("B")) {
                                if(parameters.length > 1) {
                                    int keyCode = Integer.parseInt(parameters[1]);
                                    tile = new Barricade(pos, keyCode);
                                }
                            }
                            else if(parameters[0].equals("F")) {
                                tile = new Finish(pos);
                            }
                            else if(parameters[0].equals("K")) {
                                if(parameters.length > 1) {
                                    int keyCode = Integer.parseInt(parameters[1]);
                                    tile = new KeyTile(pos, new Key(keyCode));
                                }
                            }
                            else if(parameters[0].equals("W")) {
                                tile = new Wall(pos);
                            }                        
                            if(tile == null) {
                                tile = new EmptyTile(pos);
                            }
                            tiles.put(pos, tile);                       
                        }
                    }
                    lineIndex++;
                }    
            }
            catch(Exception e)
            {

            }
        }
        for(int y = 0; y < ROWS; y++) {
            for(int x = 0; x < COLS; x++) {
                BoardCoordinate pos = new BoardCoordinate(x,y);
                if(tiles.get(pos) == null)
                    tiles.put(pos, new EmptyTile(pos));
            }
        }       
        return tiles;
    }
}
