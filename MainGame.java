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

    static final int windowsWidth = 570;
    static final int windowsHeight = 540;

    JFrame gameWindow;
    Display displayMap;
    Player player;
    Map map = new Map();

    static public int playerPositionX = 0;
    static public int playerPositionY = 0;

    MainGame () {

        JLabel objective = new JLabel();
        objective.setText("Find the battery!");
        objective.setForeground(Color.green);
        objective.setFont(new Font("Courier New", Font.BOLD, 50));
        objective.setSize(windowsWidth, windowsHeight);
        objective.setVerticalAlignment(JLabel.TOP);
        objective.setHorizontalAlignment(JLabel.CENTER);

        displayMap = new Display();
        player = new Player(this);

        this.add(objective);
        this.add(displayMap);
        this.add(player);

        this.setTitle("Robot Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(windowsWidth,windowsHeight);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.BLACK);
        this.addKeyListener(this);
    
    }
    
    public void keyTyped (KeyEvent e) {

    }

    public void keyPressed (KeyEvent e) {
        switch(e.getKeyCode()) {
            case LEFT: player.moveLeft();
            displayMap.repaint();
            break;
            case UP: player.moveUp();
            displayMap.repaint();
            break;
            case RIGHT: player.moveRight();
            displayMap.repaint();
            break;
            case DOWN: player.moveDown();
            displayMap.repaint();
            break;
        }
    }

    public void keyReleased (KeyEvent e) {

    }

}