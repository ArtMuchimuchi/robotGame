import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Display extends JPanel {
    
    final int maxBlockX = 25;
    final int maxBlockY = 20;
    final int blockSize = 20;

    public Display () {
        this.setBounds(30, 80, 500, 400);
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
    }

    public void generateMap (Block inputMap[][]) {
        for(int i=0;i<inputMap.length;i++) {
            for(int j=0;j<inputMap[0].length;j++) {
                System.out.println("["+i+"]["+j+"] = "+inputMap[i][j].getBlockValue());
            }
        }

    }

    public void createColor (Block inputBlock) {
        if(inputBlock.getBlockValue()==0) {

        }
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        
        g2.setColor(Color.pink);
        g2.fillRect(0, 0, 500, 400);
        g2.dispose();

        
    }

}
