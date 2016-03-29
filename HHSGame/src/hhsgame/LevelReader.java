package hhsgame;

import java.io.*;
import java.util.*;
import static hhsgame.Game.*;


public class LevelReader {

    private BufferedReader in = null;
    private int levelAmount;
    private String fileName;
    
    private List<Level> levels = new ArrayList<>();
    
    
    public LevelReader(String fileName) {

        this.fileName = fileName;
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
    
    public void readLevels() {
        openFile();
        String[][] lines = new String[ROWS][COLS];
        int counter = 0;
        String line;
        try {
            
            while((line = in.readLine()) != null) {
                if(counter == 10) {
                    counter = 0;
                    levels.add(new Level(lines));
                    lines = new String[ROWS][COLS];
                }
                lines[counter] = line.split(",");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<Level> getLevels() {
        return levels;
    }
}
