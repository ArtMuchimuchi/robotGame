import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Player extends JPanel {
    
    int playerPositionX = 240;
    int playerPositionY = 200;
    int playerSize = 20;

    Player () {
        this.setBounds(playerPositionX, playerPositionY, playerSize, playerSize);
    }

    public void update () {
        
    }
    
    public void paintComponent (Graphics g2) {
        g2.setColor(Color.GREEN);
        g2.fillRect(playerPositionX, playerPositionY, playerSize, playerSize);
        g2.dispose();
    }
}
