package hhsgame;

import java.io.BufferedReader;
import java.io.FileReader;

public class LevelReader {

    BufferedReader in;
    int levelAmount;
    
    
    public LevelReader(String fileName) {
        try {
            in = new BufferedReader(new FileReader(fileName));
        } catch(Exception e) {
            in = null;
            e.printStackTrace();
        }
        levelAmount = readLevelAmount();
    }
    
    private int readLevelAmount() {
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
}
