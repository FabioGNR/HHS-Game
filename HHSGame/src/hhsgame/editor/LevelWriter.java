/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhsgame.editor;

import hhsgame.Game;
import static hhsgame.Game.LEVEL_LIST_FILE;
import hhsgame.Level;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 *
 * @author Fabio
 */
public class LevelWriter {
    public static void writeLevel(String filepath, String[][] tiles) throws Exception
    {
        // appending our own extension to the file name
        filepath = filepath + ".lvl";
        // making a File in the given path
        try (FileWriter writer = new FileWriter(filepath)) {
            // writing every element of the array in to the file in lines
            for(int l = 0; l < tiles.length; l++) {
                String[] line = tiles[l];
                for(int b = 0; b < line.length; b++) {
                    String tile = line[b];
                    writer.append(tile);
                    // if we are not yet on the last element of the line, 
                    // seperate the elements by comma's
                    if(b < line.length-1) {
                        writer.append(",");
                    }
                }
                // if we are not yet on the last line, 
                // write the next line on a new line
                if(l < tiles.length-1) {
                    writer.append("\r\n");
                }
            }
        }
        addLevel(filepath);
    }
    
    // adding the level to the level list file
    public static void addLevel(String filePath) throws Exception {
        List<Level> levels = Game.getLevelReader().getLevels();
        // checking if the name doesn't already exist
        for(Level level : levels) {
            if(level.getFilename().equals(filePath)) {
                // don't add to file because it's already there
                throw new Exception("Level with this name already exists.");
            }
        } 
        try {
            // adding the name to a new line in the file
            try (FileWriter writer = new FileWriter(LEVEL_LIST_FILE, true)) {
                writer.append(filePath);
                writer.append("\r\n");
            }
            Game.getLevelReader().readLevels();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    // removing the file by removing the name from the level list
    // and writing a new file from the new list
    public static void removeLevel(Level levelToRemove) {
        List<Level> list = Game.getLevelReader().getLevels();
        list.remove(levelToRemove);
         try {
            try (FileWriter writer = new FileWriter(LEVEL_LIST_FILE)) {
                for(Level level : list) {
                    writer.append(level.getFilename() + System.lineSeparator());
                }
            }
            File levelFile = new File(levelToRemove.getFilename());
            Files.delete(levelFile.toPath());
           
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }            
    }
}
