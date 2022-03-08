import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
// import javax.swing.Action;
// import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainGame extends JFrame implements KeyListener{
    
    static final int LEFT = 37;
    static final int UP = 38;
    static final int RIGHT = 39;
    static final int DOWN = 40;
    static final int SPACEBAR = 32;

    static final int windowsWidth = 570;
    static final int windowsHeight = 540;

    JFrame gameWindow;
    Display displayMap;
    Player player;
    Map map = new Map();

    public Thread runDisplay;
    public boolean running = true;
    public String playerName;

    MainGame () {
        // JLabel objective = new JLabel();
        // objective.setText("Find the battery!");
        // objective.setForeground(Color.green);
        // objective.setFont(new Font("Courier New", Font.BOLD, 50));
        // objective.setSize(windowsWidth, windowsHeight);
        // objective.setVerticalAlignment(JLabel.TOP);
        // objective.setHorizontalAlignment(JLabel.CENTER);

        player = new Player(this);
        displayMap = new Display(player);

        // this.add(objective);
        this.add(displayMap);
        // this.add(player);

        this.setTitle("Robot Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(windowsWidth,windowsHeight);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.BLACK);
        this.addKeyListener(this);
    
        runDisplay = new Thread(new DisplayRefresher(displayMap, this, player));
        runDisplay.start();
    }

    public void setPlayerName () {
        player.playerName = this.playerName;
    }
    
    public void keyTyped (KeyEvent e) {

    }

    public void keyPressed (KeyEvent e) {
        switch(e.getKeyCode()) {
            case LEFT: player.moveLeft();
            // displayMap.repaint();
            break;
            case UP: player.moveUp();
            // displayMap.repaint();
            break;
            case RIGHT: player.moveRight();
            // displayMap.repaint();
            break;
            case DOWN: player.moveDown();
            // displayMap.repaint();
            break;
            case SPACEBAR: player.shooting();
            break;
        }
    }

    public void keyReleased (KeyEvent e) {

    }

}

class DisplayRefresher implements Runnable {

    private MainGame game;
    private Display display;
    private Player player;

    DisplayRefresher (Display targetDisplay, MainGame currentGame, Player targetPlayer) {
        this.display = targetDisplay;
        this.game = currentGame;
        this.player = targetPlayer;
    }

    @Override
    public void run() {
        while(game.running) {
            try {
                display.repaint();
                player.checkOnBlock();
                player.checkHealth();
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}