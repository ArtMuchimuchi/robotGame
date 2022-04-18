import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameFrame extends JFrame {

    GameFrame (int windowsWidth, int windowsHeight) {

        this.setTitle("Robot Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(windowsWidth,windowsHeight);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.BLACK);
    }
    
}
