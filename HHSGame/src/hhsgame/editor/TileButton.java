/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhsgame.editor;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JComponent;
import static hhsgame.Game.*;
import java.awt.Color;

/**
 *
 * @author Fabio
 */
public class TileButton extends JComponent {
    private final Class type;
    private final Image image;
    
    public TileButton(Class type, Image img)
    {
        this.type = type;
        this.image = img;
        this.setSize(TILE_WIDTH, TILE_HEIGHT);
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        if(image == null)
            return;
        g.drawImage(image, 0, 0, TILE_WIDTH, TILE_HEIGHT, null);
    }
}
