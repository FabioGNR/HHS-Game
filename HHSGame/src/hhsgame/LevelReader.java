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
        return lineCount/10;
    }
    
    public int getLevelAmount() {
        return levelAmount;
    }
    
    public Map<BoardCoordinate, Tile> getLevelTiles(int level) {
        
    }
}
