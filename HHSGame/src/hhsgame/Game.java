package hhsgame;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Game{
    // public static variables
    public final static int ROWS = 10;
    public final static int COLS = 10;
    public final static int TILE_HEIGHT = 80;
    public final static int TILE_WIDTH = 80;
    
    // private interface variables
    private final static int MENU_MARGIN = 200;
    
    protected final static int RIGHT_BOUND = TILE_WIDTH*COLS;
    protected final static int BOTTOM_BOUND = TILE_HEIGHT*ROWS;
    
    private final static int MENU_PADDING = 10;
    private final static int BUTTON_HEIGHT = 60;
    
    private final static int MAIN_MENU_WIDTH = 300;
    private final static int MAIN_MENU_HEIGHT = BUTTON_HEIGHT;
    
    private final static int FRAME_WIDTH = RIGHT_BOUND + MENU_MARGIN+2*MENU_PADDING;
    private final static int FRAME_HEIGHT = BOTTOM_BOUND;
    
    private final static String GAME_CARD_ID = "GameCard";
    private final static String MENU_CARD_ID = "MenuCard";
    //
    
    private static GameBoard board;
    private static JFrame frame;
    private static CardLayout containerLayout;//what does this do?
    private static JPanel gamePanel, menuPanel, containerPanel;

    private static LevelReader reader = new LevelReader("levels.txt");
    
    enum ButtonAction{ Reset, Pause, Menu }
    
    public static void main(String[] args) {
        board = new GameBoard();
        frame = new JFrame();
        frame.setTitle("Key Game");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        createGamePanel();
        createMenuPanel();
        containerLayout = new CardLayout();
        containerPanel = new JPanel(containerLayout);
        containerPanel.add(menuPanel, MENU_CARD_ID);
        containerPanel.add(gamePanel, GAME_CARD_ID);
        frame.add(containerPanel);
        frame.setVisible(true);
    }
    
    private static void createMenuPanel() {
        menuPanel = new JPanel();     
        menuPanel.setLayout(null);
        
        JButton startButton = new JButton();
        startButton.setSize(MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT);
        startButton.setLocation((FRAME_WIDTH-MAIN_MENU_WIDTH)/2, (FRAME_HEIGHT)/2+MENU_PADDING);
        startButton.setText("STARTE DIE SPIELE!");
               
        Integer[] levelList = new Integer[reader.getLevelAmount()];     
        for(int i = 1; i <= levelList.length; i++) {
            levelList[i-1] = i;
        }
        
        JComboBox levelSelect = new JComboBox(levelList);
//        if(levelList.length == 0) {
//            startButton.setEnabled(false);
//            startButton.setText("ES GIBT KEINE STUFEN GEFUNDEN!");
//            levelSelect.setEnabled(false);
//        } else {
//            levelSelect.setSelectedIndex(0);
//        }
        levelSelect.setSize(MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT);
        levelSelect.setLocation((FRAME_WIDTH-MAIN_MENU_WIDTH)/2, (FRAME_HEIGHT)/2-MENU_PADDING-MAIN_MENU_HEIGHT);
        
        startButton.addActionListener(new StartButtonListener(levelSelect));
        menuPanel.add(levelSelect);
        menuPanel.add(startButton);
    }
    
    private static void createGamePanel() {
        gamePanel = new JPanel();
        gamePanel.setLayout(null);//position gamePanel vertically
        gamePanel.add(board);
        
        gamePanel.add(buttonSetup(RIGHT_BOUND+MENU_PADDING, 
                                  MENU_PADDING, 
                                  "pause", 
                                  ButtonAction.Pause));
        gamePanel.add(buttonSetup(RIGHT_BOUND+MENU_PADDING, 
                                  MENU_PADDING*2+BUTTON_HEIGHT, 
                                  "reset", 
                                  ButtonAction.Reset)); 
        gamePanel.add(buttonSetup(RIGHT_BOUND+MENU_PADDING, 
                                  MENU_PADDING*3+BUTTON_HEIGHT*2, 
                                  "menu", 
                                  ButtonAction.Menu));
    }
    
    private static JButton buttonSetup(int x, int y, String text, ButtonAction action) {
        JButton button = new JButton();
        button.setLocation(x, y);
        button.setSize( MENU_MARGIN-(MENU_PADDING*2), BUTTON_HEIGHT);
        button.setText(text);
        button.addActionListener(new ButtonClickListener(action));
        return button;
    }
    
    // Maybe not static? Was necessary for the ButtonSetup.
    static class ButtonClickListener implements ActionListener{
        ButtonAction action;
        public ButtonClickListener(ButtonAction action){
            this.action = action;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if(action == ButtonAction.Pause) {
                board.togglePause();
            } else if(action == ButtonAction.Reset) {
                board.reset(reader);
            } else if(action == ButtonAction.Menu) {
                containerLayout.show(containerPanel, MENU_CARD_ID);
            }
        }      
    }
    
    static class StartButtonListener implements ActionListener {      
        JComboBox dropDown;
        public StartButtonListener(JComboBox dropDown) {
            this.dropDown = dropDown;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int level = dropDown.getSelectedIndex();
            containerLayout.show(containerPanel, GAME_CARD_ID);
            board.grabFocus();
            board.loadLevel(reader, level);
        }
    }
}
