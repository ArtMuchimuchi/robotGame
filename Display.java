import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Display extends JPanel {
    
    final int maxBlockX = 25;
    final int maxBlockY = 20;
    final int blockSize = 20;

    public Display () {
        this.setBounds(30, 80, 500, 400);
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
    }

    public void setColor (int blockValue, Graphics2D g2) {
        if(blockValue == 1) {
            Color grass = new Color(53, 154, 46);
            g2.setColor(grass);
        }
        else if(blockValue == 2) {
            Color bomb = new Color(90, 67, 23);
            g2.setColor(bomb);
        }
        else if(blockValue == 3) {
            Color battery = new Color(252, 252, 18);
            g2.setColor(battery);
        }
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        

        for(int i=0 ;i<maxBlockX;i++) {
            for(int j=0;j<maxBlockY;j++) {

                int startDisplayX = MainGame.playerPositionX - 12;
                int startDisplayY = MainGame.playerPositionY - 9;

                if((startDisplayX + i >= 0 && startDisplayX + i < 100)&&(startDisplayY + j >= 0 && startDisplayY + j < 80)) {
                    setColor(Map.map[i+startDisplayX][j+startDisplayY], g2);
                    g2.fillRect(i * blockSize,j * blockSize, blockSize, blockSize);
                }
            }
        }

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Courier New", Font.BOLD, 24));
        g2.drawString("X:"+MainGame.playerPositionX+" Y:"+MainGame.playerPositionY
        , 5, 20*20 - 5);

        g2.dispose();
    }

}
