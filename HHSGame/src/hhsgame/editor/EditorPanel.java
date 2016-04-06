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
import hhsgame.Level;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Fabio
 */
public class EditorPanel extends JPanel {
    
    private final Editor editor;
    private enum ButtonAction {
        Save, Reset
    }
    private final JTextField levelNameField, keyCodeField;
    private final int TEXT_FIELD_HEIGHT = BUTTON_HEIGHT-20;
    private TileButton selectedTileButton = null;
    
    public EditorPanel(ActionListener menuOpener, int width, int height)
    {
        
        setLayout(null);
        levelNameField = new JTextField();
        levelNameField.setSize(MENU_MARGIN-(MENU_PADDING*2), TEXT_FIELD_HEIGHT);
        levelNameField.setLocation(RIGHT_BOUND+MENU_PADDING, MENU_PADDING);
        add(levelNameField);
        JButton saveButton = new JButton();
        saveButton.setText("Save Level");
        saveButton.setLocation(RIGHT_BOUND+MENU_PADDING, MENU_PADDING*2+TEXT_FIELD_HEIGHT);
        saveButton.setSize(MENU_MARGIN-(MENU_PADDING*2), BUTTON_HEIGHT);
        saveButton.addActionListener(new ButtonListener(ButtonAction.Save));
        add(saveButton);
        JButton resetButton = new JButton();
        resetButton.setText("Reset Level");
        resetButton.setLocation(RIGHT_BOUND+MENU_PADDING, 
                MENU_PADDING*3+BUTTON_HEIGHT+TEXT_FIELD_HEIGHT);
        resetButton.setSize(MENU_MARGIN-(MENU_PADDING*2), BUTTON_HEIGHT);
        resetButton.addActionListener(new ButtonListener(ButtonAction.Reset));
        add(resetButton);
        JButton menuButton = new JButton();
        menuButton.setText("Menu");
        menuButton.setLocation(RIGHT_BOUND+MENU_PADDING, MENU_PADDING*4+BUTTON_HEIGHT*2+TEXT_FIELD_HEIGHT);
        menuButton.setSize(MENU_MARGIN-(MENU_PADDING*2), BUTTON_HEIGHT);
        menuButton.addActionListener(menuOpener);
        add(menuButton);
        keyCodeField = new JTextField();
        keyCodeField.setLocation(RIGHT_BOUND+MENU_PADDING, MENU_PADDING*5+BUTTON_HEIGHT*3+TEXT_FIELD_HEIGHT);
        keyCodeField.setSize(MENU_MARGIN-(MENU_PADDING*2), TEXT_FIELD_HEIGHT);
        keyCodeField.getDocument().addDocumentListener(new KeyCodeFieldChanged());
        add(keyCodeField);

        createTileButtons();
        editor = new Editor(width, height);
        add(editor);
        editor.repaint();
        repaint();
    }
    
    public void openLevel(Level level) {
        editor.openLevel(level);
        levelNameField.setText(level.getFilename().replace(".lvl", ""));
    }
    
    public void resetLevel() {
        editor.reset();
    }
    
    private void createTileButton(TileType type, int y)
    {
        int tileButtonX = RIGHT_BOUND+MENU_PADDING;
        createTileButton(type, tileButtonX, y);
    }
    
    private void createTileButton(TileType type, int x, int y) {
        TileButton button = new TileButton(type, x, y);
        button.addMouseListener(new TileButtonListener());
        add(button); 
    }
    
    private void createTileButtons()
    {
        int startY = MENU_PADDING*6+BUTTON_HEIGHT*3+TEXT_FIELD_HEIGHT*2;
        createTileButton(TileType.Empty, startY);
        createTileButton(TileType.Key, startY+TILE_HEIGHT+MENU_PADDING);
        createTileButton(TileType.Barricade, startY+(TILE_HEIGHT+MENU_PADDING)*2);
        createTileButton(TileType.Wall, startY+(TILE_HEIGHT+MENU_PADDING)*3);
        createTileButton(TileType.Finish, startY+(TILE_HEIGHT+MENU_PADDING)*4);
        createTileButton(TileType.Start, RIGHT_BOUND+MENU_PADDING*2+TILE_WIDTH, startY);
    }
    
    private int getKeyCode() {
        String keyCodeStr = keyCodeField.getText();
        int keyCode;
        try {
            keyCode = Integer.parseInt(keyCodeStr);
        }
        catch(NumberFormatException ex) {
            keyCode = 0;
        }
        return keyCode;
    }
    
    protected class KeyCodeFieldChanged implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            setKeyCode();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            setKeyCode();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            setKeyCode();
        }
    
        private void setKeyCode() {
            if(selectedTileButton != null) {
                editor.setTileType(selectedTileButton.getType(), getKeyCode());
            }
        }
    }
    
    private void showMessageDialog(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
    
    protected class ButtonListener implements ActionListener {
        private final ButtonAction action;
        protected ButtonListener(ButtonAction action) {
            this.action = action;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if(action == ButtonAction.Save) {
                String filepath = levelNameField.getText();
                if(!filepath.isEmpty()) {
                    try {
                        editor.save(filepath);
                    }
                    catch(Exception ex) {
                        showMessageDialog(ex.getMessage());
                    }
                }
            }
            else if(action == ButtonAction.Reset) {
                resetLevel();
            }
        }
    }
    
    protected class TileButtonListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }
        @Override
        public void mousePressed(MouseEvent e) {
            TileButton source = (TileButton)e.getSource();
            if(selectedTileButton != null) {
                selectedTileButton.setSelectedState(false);
            }
            selectedTileButton = source;
            source.setSelectedState(true);
            editor.setTileType(source.getType(), getKeyCode());
        }
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
