/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhsgame;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Fabio
 */
public class ImageReader {
    public static Image getImage(String path)
    {
        File file;
        try {
            file = new File(path);
        }
        catch(Exception e)
        {
            return null;
        }
        Image image;
        try {
            image = ImageIO.read(file);
        } catch (IOException ex) {
            return null;
        }
        return image;
    }
}
