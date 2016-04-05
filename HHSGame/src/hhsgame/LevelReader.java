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
    
    private void openFile(String fileName){
        try {
            in = new BufferedReader(new FileReader(fileName));
        } catch(Exception e) {
            in = null;
            System.out.println("File was not found.");
            System.exit(0);
        }
    }
    
//    private void readLevels() {
//        openFile();
//        String[][] lines = new String[ROWS][COLS];
//        int counter = 0;
//        String line;
//        try {
//            
//            while((line = in.readLine()) != null) {
//                lines[counter] = line.split(",");
//                counter++;
//                if(counter == 10) {
//                    counter = 0;
//                    levels.add(new Level(lines));
//                    lines = new String[ROWS][COLS];
//                    in.readLine();
//                }
//            }
//        } catch(IOException e) {
//            e.printStackTrace();
//        }
//    }
    
    private void readLevels() {
        openFile(fileName);
        String[][] lines = new String[ROWS][COLS];
        List<String> levelNames = new ArrayList<String>();
        String line;
        try {
            while((line = in.readLine()) != null) {
                levelNames.add(line);
            }
            for(String level : levelNames) {
                openFile(level);
                line = in.readLine();
                for(int i = 0; i < 10; i++) {
                    lines[i] = line.split(",");
                }
                levels.add(new Level(lines));
                lines = new String[ROWS][COLS];
            }
        } catch(IOException e) {
            return;
        }
    }
    
public List<Level> getLevels() {
    return levels;
    }
}
