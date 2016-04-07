package hhsgame;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 *
 * @author Fabio
 */
public class ImageReader {
    //getImage method tries to open, read and return the image file.
    //if the file can't be opened or image can't be read, it catches the error and return null. 
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
