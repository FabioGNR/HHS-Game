/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhsgame.editor;

import hhsgame.Game;
import hhsgame.Level;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Fabio
 */
public class LevelWriter {
    public static void writeLevel(String filepath, String[][] tiles) throws IOException
    {
        filepath = filepath + ".lvl";
        FileWriter writer = new FileWriter(filepath);
        for(int l = 0; l < tiles.length; l++) {
            String[] line = tiles[l];
            for(int b = 0; b < line.length; b++) {
                String tile = line[b];
                writer.append(tile);
                if(b < line.length-1) {
                    writer.append(",");
                }
            }
            if(l < tiles.length-1) {
                writer.append("\r\n");   
            }
        }
        writer.close();       
        addLevel(filepath);
    }
    
    public static void addLevel(String filePath) {
        List<Level> levels = Game.getLevelReader().getLevels();
        for(Level level : levels) {
            if(level.getFilename().equals(filePath)) {
                // don't add to file because it's already there
                return;
            }
        } 
        try {
            FileWriter writer = new FileWriter("levels.txt", true);
            writer.append(filePath);
            writer.append("\r\n");
            writer.close();
            Game.getLevelReader().readLevels();
        } catch(IOException e) {
            e.printStackTrace();
            return;
        }
    }
}
