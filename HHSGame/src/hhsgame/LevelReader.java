package hhsgame;

import java.io.*;
import java.util.*;
import static hhsgame.Game.*;

public class LevelReader {

    private BufferedReader in = null;
    private final String fileName;
    private final List<Level> levels = new ArrayList<>();
    
    //method readLevels() is used when LevelReader is instantiated 
    //and reads the specified txt file ( this file contains the names of all levels )
public LevelReader(String fileName) {
        // store the name of the list file and read it's levels
        this.fileName = fileName;
        readLevels();
    }
    
    // attempts to open a file
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
    
    // attempts to open the level list file and sets it as class member for later use
    private void openFile(String fileName){
        try {
            in = new BufferedReader(new FileReader(fileName));
        } catch(Exception e) {
            in = null;
            System.out.println("Level list file was not found.");
        }
    }
    
    // (re)opens the level list file and (re)loads all levels
    public void readLevels() {        
        // make sure we don't add old levels again by clearing the list
        levels.clear();
        // opens level list 
        openFile(fileName);
        if(in == null) {
            return;
        }
        
        List<String> levelNames = new ArrayList<>();
        String line;
        try {
            // go over each line in the list and add it to list of level names
            while((line = in.readLine()) != null) {
                if(!line.isEmpty()) { // ignore empty lines
                    levelNames.add(line);
                }
            }
            // attempt to open each found level
            for(String level : levelNames) {   
                try (BufferedReader reader = openLevelFile(level)) {
                    String[][] lines = new String[ROWS][COLS];
                    if(reader == null) { // file could not be opened
                        continue;
                    }
                    // reads the level file and stores its lines
                    for(int i = 0; i < ROWS; i++) {
                        line = reader.readLine();
                        lines[i] = line.split(",");
                    }
                    // load the level using Level and store it in the list
                    levels.add(new Level(lines, level));
                }
            }
            in.close(); // closes the level list file because we're done reading from it ( for now )
        } catch(Exception e) {
        }
    }
    
    public List<Level> getLevels() {
        return levels;
    }
}
