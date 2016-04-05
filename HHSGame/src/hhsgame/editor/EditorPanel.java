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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Fabio
 */
public class EditorPanel extends JPanel {
    
    private Editor editor;
    private JButton saveButton, menuButton;
    private JTextField levelNameField, keyCodeField;
    private TileButton selectedTileButton = null;
    
    public EditorPanel(ActionListener menuOpener)
    {
        setLayout(null);
        levelNameField = new JTextField();
        levelNameField.setLocation(RIGHT_BOUND+MENU_PADDING, MENU_PADDING);
        levelNameField.setSize(MENU_MARGIN-(MENU_PADDING*2), BUTTON_HEIGHT);       
        add(levelNameField);
        saveButton = new JButton();
        saveButton.setLocation(RIGHT_BOUND+MENU_PADDING, MENU_PADDING*2+BUTTON_HEIGHT);
        saveButton.setSize(MENU_MARGIN-(MENU_PADDING*2), BUTTON_HEIGHT);
        saveButton.addActionListener(new SaveListener());
        add(saveButton);
        menuButton = new JButton();
        menuButton.setLocation(RIGHT_BOUND+MENU_PADDING, MENU_PADDING*3+BUTTON_HEIGHT*2);
        menuButton.setSize(MENU_MARGIN-(MENU_PADDING*2), BUTTON_HEIGHT);
        menuButton.addActionListener(menuOpener);
        add(menuButton);
        keyCodeField = new JTextField();
        keyCodeField.setLocation(RIGHT_BOUND+MENU_PADDING, MENU_PADDING*4+BUTTON_HEIGHT*3);
        keyCodeField.setSize(MENU_MARGIN-(MENU_PADDING*2), BUTTON_HEIGHT);
        add(keyCodeField);

        createTileButtons();
    }
    
    private void createTileButton(TileType type, int y)
    {
        int tileButtonX = RIGHT_BOUND+MENU_PADDING;
        TileButton button = new TileButton(type, tileButtonX, y);
        add(button);
    }
    
    private void createTileButtons()
    {
        int startY = MENU_PADDING*5+BUTTON_HEIGHT*4;
        createTileButton(TileType.Empty, startY);
        createTileButton(TileType.Key, startY+TILE_HEIGHT+MENU_PADDING);
        createTileButton(TileType.Barricade, startY+(TILE_HEIGHT+MENU_PADDING)*2);
        createTileButton(TileType.Wall, startY+(TILE_HEIGHT+MENU_PADDING)*3);
        createTileButton(TileType.Finish, startY+(TILE_HEIGHT+MENU_PADDING)*4);   
    }
    
    protected class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String filepath = levelNameField.getText();
            if(!filepath.isEmpty()) {
                try {
                    editor.save(filepath);
                }
                catch(IOException e) {
                    
                }
            }
        }
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
            String keyCodeStr = keyCodeField.getText();
            int keyCode;
            try {
                keyCode = Integer.parseInt(keyCodeStr);
            }
            catch(NumberFormatException ex) {
                keyCode = 0;
            }
            editor.setTileType(source.getType(), keyCode);
        }    
    }
}
