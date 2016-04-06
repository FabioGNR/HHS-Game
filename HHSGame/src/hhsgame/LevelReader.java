package hhsgame;

import java.io.*;
import java.util.*;
import static hhsgame.Game.*;

public class LevelReader {

    private BufferedReader in = null;
    private final String fileName;
    private final List<Level> levels = new ArrayList<>();
    
    //method readLevels() is used when LevelReader is instantiated and read the specified txt file
public LevelReader(String fileName) {
        this.fileName = fileName;
        readLevels();
    }

    private BufferedReader openLevelFile(String fileName) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch(Exception e) {
            reader = null;
            System.out.println("Level \""+fileName+"\" file was not found.");
        }    
        return reader;
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
        if(in == null) {
            return;
        }
        String[][] lines = new String[ROWS][COLS];
        List<String> levelNames = new ArrayList<>();
        String line;
        try {
            while((line = in.readLine()) != null) {
                if(!line.isEmpty()) { // ignore empty lines
                    levelNames.add(line);
                }
            }
            for(String level : levelNames) {   
                try (BufferedReader reader = openLevelFile(level)) {
                    if(reader == null) { // file could not be opened
                        continue;
                    }
                    for(int i = 0; i < 10; i++) {
                        line = reader.readLine();
                        lines[i] = line.split(",");
                    }
                    levels.add(new Level(lines, level));
                    lines = new String[ROWS][COLS];
                }
            }
            in.close();
        } catch(Exception e) {
        }
    }
    
    public List<Level> getLevels() {
        return levels;
    }
}
