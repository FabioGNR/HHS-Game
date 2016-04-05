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
    private final TileType type;
    boolean selected = false;
    
    public TileButton(TileType type, int x, int y)
    {
        this.type = type;
        this.setLocation(x, y);
        this.setSize(TILE_WIDTH, TILE_HEIGHT);
    }
    
    public void setSelectedState(boolean state)
    {
        selected = state;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        Image image = type.getImage();
        if(image == null)
            return;
        g.drawImage(image, 0, 0, TILE_WIDTH, TILE_HEIGHT, null);
        if(selected)
        {
            g.setColor(Color.RED);
            g.drawRect(0,0, TILE_WIDTH, TILE_HEIGHT);
            g.setColor(Color.BLACK);
        }
    }
}
