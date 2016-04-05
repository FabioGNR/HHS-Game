/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhsgame.editor;

import hhsgame.Barricade;
import hhsgame.EmptyTile;
import static hhsgame.Game.*;
import hhsgame.KeyTile;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Fabio
 */
public class EditorPanel extends JPanel {
    
    private Editor editor;
    private JButton saveButton, menuButton;
    private TileButton emptyTileButton, keyTileButton, barricadeButton;
    private TileButton wallButton, finishButton;
    
    public EditorPanel()
    {
        setLayout(null);
        saveButton = new JButton();
        saveButton.setLocation(RIGHT_BOUND+MENU_PADDING, MENU_PADDING);
        saveButton.setSize(MENU_MARGIN-(MENU_PADDING*2), BUTTON_HEIGHT);
        menuButton = new JButton();
        menuButton.setLocation(RIGHT_BOUND+MENU_PADDING, MENU_PADDING*2+BUTTON_HEIGHT);
        menuButton.setSize(MENU_MARGIN-(MENU_PADDING*2), BUTTON_HEIGHT);
        
    }
    
    private void createTileButtons()
    {
        emptyTileButton = new TileButton(EmptyTile.class, EmptyTile.getImage());
        keyTileButton = new TileButton(KeyTile.class, KeyTile.getImage());
        barricadeButton = new TileButton(Barricade.class, Barricade.getImage());
        
    }
}
