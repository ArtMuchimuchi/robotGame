import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

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
    public String playerName;
    public Client client;
    public Thread clientThread;

    MainGame () {
        client = new Client();
        clientThread = new Thread(client);
        clientThread.start();

        player = new Player(this);
        displayMap = new Display(player, this);

        this.add(displayMap);

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
            break;
            case UP: player.moveUp();
            break;
            case RIGHT: player.moveRight();
            break;
            case DOWN: player.moveDown();
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
        while(!game.client.player.done) {
            try {
                display.repaint();
                player.updatePlayerData();
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        boolean ifDie;

        if(game.client.player.playerHealth > 0) {
            ifDie = false;
        }
        else {
            ifDie = true;
        }

        Result result = new Result(ifDie, game.playerName);
        game.dispose();
        
    }

}