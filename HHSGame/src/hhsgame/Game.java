package hhsgame;

import hhsgame.editor.EditorPanel;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game{
    public enum MoveDirection { 
        Up {
            @Override
            public BoardCoordinate getCoordinate(BoardCoordinate pos) {
                return pos.getUp();
            }            
        }, 
        Down {
            @Override
            public BoardCoordinate getCoordinate(BoardCoordinate pos) {
                return pos.getDown();
            }            
        }        
        , Left {
            @Override
            public BoardCoordinate getCoordinate(BoardCoordinate pos) {
                return pos.getLeft();
            }  
        }       
        , Right {
            @Override
            public BoardCoordinate getCoordinate(BoardCoordinate pos) {
                return pos.getRight();
            }  
        };     
        public abstract BoardCoordinate getCoordinate(BoardCoordinate pos);
    };
    // public static variables
    public final static int ROWS = 10;
    public final static int COLS = 10;
    public final static int TILE_HEIGHT = 80;
    public final static int TILE_WIDTH = 80;
    public final static int MENU_PADDING = 10;
    public final static int BUTTON_HEIGHT = 60;
    public final static Font LABEL_FONT = new Font("Calibri", Font.BOLD, 22);
    public final static int RIGHT_BOUND = TILE_WIDTH*COLS;
    public final static int BOTTOM_BOUND = TILE_HEIGHT*ROWS;
    public final static int MENU_MARGIN = 200;    
    
    // private interface variables
    
    private final static int MAIN_MENU_WIDTH = 300;
    private final static int MAIN_MENU_HEIGHT = BUTTON_HEIGHT;
    
    private final static int FRAME_WIDTH = COLS*TILE_WIDTH + MENU_MARGIN+2*MENU_PADDING;
    private final static int FRAME_HEIGHT = ROWS*TILE_HEIGHT+39;
    
    private final static String GAME_CARD_ID = "GameCard";
    private final static String MENU_CARD_ID = "MenuCard";
    private final static String EDITOR_CARD_ID = "EditorCard";
    //
    
    private static GameBoard board;
    private static JFrame frame;
    private static CardLayout containerLayout;
    private static JPanel gamePanel, menuPanel, containerPanel;
    private static EditorPanel editorPanel;
    private static JComboBox levelSelect;
    private static JButton startButton;

    private static final LevelReader reader = new LevelReader("levels.txt");
    
    // An enum to limit the actions a button can have because Strings are ugly for that
    enum ButtonAction{
        Reset, Pause, Menu, Start
    }
    
    public static void main(String[] args) {
        board = new GameBoard(FRAME_WIDTH, FRAME_HEIGHT);
        frame = new JFrame();
        frame.setTitle("Key Game");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        createGamePanel();
        editorPanel = new EditorPanel(new MenuOpener(), FRAME_WIDTH, FRAME_HEIGHT);
        createMenuPanel();
        containerLayout = new CardLayout();
        containerPanel = new JPanel(containerLayout);
        containerPanel.add(menuPanel, MENU_CARD_ID);
        containerPanel.add(gamePanel, GAME_CARD_ID);
        containerPanel.add(editorPanel, EDITOR_CARD_ID);
        frame.add(containerPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private static void createMenuPanel() {
        menuPanel = new JPanel();     
        menuPanel.setLayout(null);
        
        startButton = new JButton();
        startButton.setSize(MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT);
        startButton.setLocation((FRAME_WIDTH-MAIN_MENU_WIDTH)/2, (FRAME_HEIGHT)/2+MENU_PADDING);
               
        levelSelect = new JComboBox();
        fillLevelList();
        levelSelect.setSize(MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT);
        levelSelect.setLocation((FRAME_WIDTH-MAIN_MENU_WIDTH)/2, (FRAME_HEIGHT)/2-MENU_PADDING-MAIN_MENU_HEIGHT);
        JButton editorButton = new JButton();
        editorButton.setSize(MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT);
        editorButton.setLocation((FRAME_WIDTH-MAIN_MENU_WIDTH)/2, (FRAME_HEIGHT)/2+MENU_PADDING*2+MAIN_MENU_HEIGHT);
        editorButton.setText("STARTE DIE EDITOR!");        
        editorButton.addActionListener(new EditorButtonListener());
        startButton.addActionListener(new ButtonClickListener(ButtonAction.Start));
        menuPanel.add(levelSelect);
        menuPanel.add(startButton);
        menuPanel.add(editorButton);
    }
    
    private static Level getSelectedLevel() {
        int level = levelSelect.getSelectedIndex();
        return reader.getLevels().get(level);
        
    }
    
    private static void fillLevelList() {
        levelSelect.removeAllItems();
        reader.readLevels();
        List<Level> levels = reader.getLevels();    
        System.out.println("levelCount: "+levels.size());
        for(int i = 0; i < levels.size(); i++) {
            String name = levels.get(i).getFilename();
            if(name.contains(".")) {
                name = name.replace(".lvl", "");
            }
            levelSelect.addItem(name);
        }       
        if(levelSelect.getItemCount() == 0) {
            startButton.setEnabled(false);
            startButton.setText("ES GIBT KEINE STUFEN GEFUNDEN!");
            levelSelect.setEnabled(false);
        } else {
            startButton.setEnabled(true);
            levelSelect.setEnabled(true);
            startButton.setText("STARTE DIE SPIELE!");
            levelSelect.setSelectedIndex(0);
        }
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
    
    private static void openMenu() {
        // recheck for levels
        fillLevelList();
        // show the card
        containerLayout.show(containerPanel, MENU_CARD_ID);
    }
    
    static class MenuOpener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            openMenu();
        }
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
                openMenu(); 
            } else if(action == ButtonAction.Start) {
                containerLayout.show(containerPanel, GAME_CARD_ID);
                board.grabFocus();
                board.loadLevel(reader, getSelectedLevel());
                board.repaint();   
            }
        }      
    }
    
    static class EditorButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            containerLayout.show(containerPanel, EDITOR_CARD_ID);
            editorPanel.openLevel(getSelectedLevel());
        }
    }
}
