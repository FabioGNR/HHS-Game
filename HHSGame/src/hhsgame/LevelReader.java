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
    
    private void openFile(){
        try {
            in = new BufferedReader(new FileReader(fileName));
        } catch(Exception e) {
            in = null;
            System.out.println("File was not found.");
            System.exit(0);
        }
    }
    
    private void readLevels() {
        openFile();
        String[][] lines = new String[ROWS][COLS];
        int counter = 0;
        String line;
        try {
            
            while((line = in.readLine()) != null) {
                lines[counter] = line.split(",");
                counter++;
                if(counter == 10) {
                    counter = 0;
                    levels.add(new Level(lines));
                    lines = new String[ROWS][COLS];
                    in.readLine();
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
public List<Level> getLevels() {
    return levels;
    }
}
