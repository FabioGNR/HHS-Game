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
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Fabio
 */
public class TileButton extends JComponent {
    private final TileType type;
    private boolean selected = false;
    
    public TileButton(TileType type, int x, int y)
    {
        this.type = type;
        this.setLocation(x, y);
        this.setSize(TILE_WIDTH, TILE_HEIGHT);
    }

    public TileType getType() {
        return type;
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
        float widthF = (float)(TILE_WIDTH)/(float)image.getWidth(null);
        float heightF = (float)(TILE_HEIGHT)/(float)image.getHeight(null);
        float factor = Math.min(widthF, heightF);
        int width = (int) (image.getWidth(null)*factor), height = (int) (image.getHeight(null)*factor);
        g.drawImage(image, 0, 0, width, height, null);
        if(selected)
        {
            Graphics2D g2 = (Graphics2D)g;
            g2.setStroke(new BasicStroke(10));
            g.setColor(Color.RED);
            g.drawRect(0,0, TILE_WIDTH, TILE_HEIGHT);
            g.setColor(Color.BLACK);
        }
    }
}
