package hhsgame;

import java.io.*;
import java.util.*;
import static hhsgame.Game.*;

public class LevelReader {

    private BufferedReader in = null;
    private final String fileName;
    private final List<Level> levels = new ArrayList<>();
    
    
public LevelReader(String fileName) {
        this.fileName = fileName;
        readLevels();
    }

    private void openLevelFile(String fileName) {
        try {
            in = new BufferedReader(new FileReader(fileName));
        } catch(Exception e) {
            in = null;
            System.out.println("Level \""+fileName+"\" file was not found.");
        }        
    }
    
    private void openFile(String fileName){
        try {
            in = new BufferedReader(new FileReader(fileName));
        } catch(Exception e) {
            in = null;
            System.out.println("Level list file was not found.");
        }
    }
    
    public void readLevels() {        
        levels.clear();
        openFile(fileName);
        String[][] lines = new String[ROWS][COLS];
        List<String> levelNames = new ArrayList<>();
        String line;
        try {
            while((line = in.readLine()) != null) {
                levelNames.add(line);
            }
            for(String level : levelNames) {
                if(level.isEmpty()) // empty lines should be skipped
                    continue;
                openLevelFile(level);                
                for(int i = 0; i < 10; i++) {
                    line = in.readLine();
                    lines[i] = line.split(",");
                }
                levels.add(new Level(lines, level));
                lines = new String[ROWS][COLS];
            }
        } catch(Exception e) {
            return;
        }
    }
    
    public List<Level> getLevels() {
        return levels;
    }
}
