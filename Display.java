import java.awt.Color;

import javax.swing.JPanel;

public class Display extends JPanel {
    
    final int maxBlockX = 25;
    final int maxBlockY = 20;

    public Display () {
        this.setBounds(30, 80, 500, 400);
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
    }
}
