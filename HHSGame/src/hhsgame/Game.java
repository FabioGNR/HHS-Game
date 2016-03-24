package hhsgame;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JComponent{
    protected static int ROWS = 10;
    protected static int COLS = 10;
    protected static int TILE_HEIGHT = 80;
    protected static int TILE_WIDTH = 80;
    private static int MENU_MARGIN = 200;
    
    protected static int RIGHT_BOUND = TILE_WIDTH*COLS;
    protected static int BOTTOM_BOUND = TILE_HEIGHT*ROWS;
    
    private static int MENU_PADDING = 10;
    private static int BUTTON_HEIGHT = 60;
    
    private static int MAIN_MENU_WIDTH = 300;
    private static int MAIN_MENU_HEIGHT = BUTTON_HEIGHT;
    
    private static int FRAME_WIDTH = COLS*TILE_WIDTH + MENU_MARGIN+2*MENU_PADDING;
    private static int FRAME_HEIGHT = ROWS*TILE_HEIGHT;
    
    private static GameBoard board;
    
    private static JFrame frame;
    private static JPanel gamePanel, menuPanel, containerPanel;

    private static LevelReader reader = new LevelReader("levels.txt");
    
    enum ButtonAction{
        Reset, Pause, Menu
    }
    
    protected static int getScreenX(BoardCoordinate coord) {
        return TILE_WIDTH*coord.getX();
    }
    protected static int getScreenY(BoardCoordinate coord) {
        return TILE_HEIGHT*coord.getY();
    }
    
    public static void main(String[] args) {
        board = new GameBoard();
        frame = new JFrame();
        frame.setTitle("Key Game");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        createGamePanel();
        createMenuPanel();

        containerPanel = new JPanel(new CardLayout());
        
        frame.add(menuPanel);
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
        if(levelList.length == 0) {
            startButton.setEnabled(false);
            startButton.setText("ES GIBT KEINE STUFEN GEFUNDEN!");
            levelSelect.setEnabled(false);
        } else {
            levelSelect.setSelectedIndex(0);
        }
        levelSelect.setSize(MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT);
        levelSelect.setLocation((FRAME_WIDTH-MAIN_MENU_WIDTH)/2, (FRAME_HEIGHT)/2-MENU_PADDING-MAIN_MENU_HEIGHT);
        
        startButton.addActionListener(new StartButtonListener(levelSelect));
        menuPanel.add(levelSelect);
        menuPanel.add(startButton);
    }
    
    private static void createGamePanel() {
        gamePanel = new JPanel();
        gamePanel.setLayout(null);
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
    public static class ButtonClickListener implements ActionListener{

        ButtonAction action;
        public ButtonClickListener(ButtonAction action){
            this.action = action;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if(action == ButtonAction.Pause) {
                board.togglePause();
            } else if(action == ButtonAction.Reset) {
                board.reset();
            } else if(action == ButtonAction.Menu) {
                frame.removeAll();
                frame.add(menuPanel);
            }
        }
        
    }
    
    public static class StartButtonListener implements ActionListener {
        
        JComboBox dropDown;
        public StartButtonListener(JComboBox dropDown) {
            this.dropDown = dropDown;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int level = dropDown.getSelectedIndex();
//            frame.removeAll();
            frame.add(gamePanel);
            board.grabFocus();
            board.loadLevel(level);
            frame.repaint();
        }
    }
}
