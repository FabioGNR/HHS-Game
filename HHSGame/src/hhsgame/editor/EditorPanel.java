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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Fabio
 */
public class EditorPanel extends JPanel {
    
    private Editor editor;
    private JButton saveButton, menuButton;
    private List<TileButton> tileButtons;
    private TileButton selectedTileButton = null;
    
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
    
    private void createTileButton(TileType type, int y)
    {
        int tileButtonX = RIGHT_BOUND+MENU_PADDING;
        TileButton button = new TileButton(type, tileButtonX, y);
        tileButtons.add(button);
    }
    
    private void createTileButtons()
    {
        tileButtons = new ArrayList<>();
        int startY = MENU_PADDING*3+BUTTON_HEIGHT*2;
        createTileButton(TileType.Empty, startY);
        createTileButton(TileType.Key, startY+TILE_HEIGHT+MENU_PADDING);
        createTileButton(TileType.Barricade, startY+(TILE_HEIGHT+MENU_PADDING)*2);
        createTileButton(TileType.Wall, startY+(TILE_HEIGHT+MENU_PADDING)*3);
        createTileButton(TileType.Finish, startY+(TILE_HEIGHT+MENU_PADDING)*4);   
    }
    
    protected class TileButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            TileButton source = (TileButton)e.getSource();
            if(selectedTileButton != null) {
                selectedTileButton.setSelectedState(false);
            }
            selectedTileButton = source;
            source.setSelectedState(true);
            editor.setTileType(source.getType(), 1);
        }    
    }
}
