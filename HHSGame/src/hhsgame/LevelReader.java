package hhsgame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;
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
            if(in != null) {
                in.close();
            }
            in = new BufferedReader(new FileReader(fileName));
        } catch(Exception e) {
            in = null;
        }
        
        
        
    }
    
    private int readLevelAmount() {
        openFile();
        if(in == null) {
            return 0;
        }
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
