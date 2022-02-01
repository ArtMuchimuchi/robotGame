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

public class MainGame extends JPanel implements KeyListener{
    
    static final int LEFT = 37;
    static final int UP = 38;
    static final int RIGHT = 39;
    static final int DOWN = 40;

    static final int windowsWidth = 800;
    static final int windowsHeight = 600;

    GameFrame gameFrame;
    Display displayMap;
    Player player;

    MainGame () {
        gameFrame = new GameFrame(windowsWidth, windowsHeight);

        JLabel objective = new JLabel();
        objective.setText("Find the battery!");
        objective.setForeground(Color.green);
        objective.setFont(new Font("Courier New", Font.BOLD, 50));
        objective.setSize(windowsWidth, windowsHeight);
        objective.setVerticalAlignment(JLabel.TOP);
        objective.setHorizontalAlignment(JLabel.CENTER);

        displayMap = new Display();
        player = new Player();

        gameFrame.add(objective);
        // gameFrame.add(displayMap);
        gameFrame.add(player);
        this.addKeyListener(this);
    
    }
    
    public void keyTyped (KeyEvent e) {

    }

    public void keyPressed (KeyEvent e) {
        switch(e.getKeyCode()) {
            case LEFT: 
            break;
            case UP: 
            break;
            case RIGHT: 
            break;
            case DOWN: 
            break;
        }
    }

    public void keyReleased (KeyEvent e) {

    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        player.paint(g2);
        
    }
}