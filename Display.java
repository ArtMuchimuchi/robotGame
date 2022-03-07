import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

public class Display extends JPanel {
    
    final int startX = 30;
    final int startY = 80;
    final int maxBlockX = 25;
    final int maxBlockY = 20;
    final int blockSize = 20;
    final int minMapX = 0;
    final int maxMapX = 100;
    final int minMapY = 0;
    final int maxMapY = 80;

    public BufferedImage tankUp, tankDown, tankLeft, tankRight;

    Player player;

    public Display (Player targetPlayer) {
        this.setBounds(0, 0, MainGame.windowsWidth, MainGame.windowsHeight);
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.player = targetPlayer;
        getImage();
    }

    public void setColor (int blockValue, Graphics2D g2) {
        if(blockValue == Map.grassBlock) {
            Color grass = new Color(53, 154, 46);
            g2.setColor(grass);
        }
        else if(blockValue == Map.bombBlock) {
            Color bomb = new Color(90, 67, 23);
            g2.setColor(bomb);
        }
        else if(blockValue == Map.batteryBlock) {
            Color battery = new Color(252, 252, 18);
            g2.setColor(battery);
        }
        else if(blockValue == Map.bullet) {
            Color bullet = new Color(0, 255, 255);
            g2.setColor(bullet);
        }
    }

    public BufferedImage convertDirectionToImage (int playerDirection) {
        if(playerDirection == Player.UP) {
            return tankUp;
        }
        else if(playerDirection == Player.LEFT) {
            return tankLeft;
        }
        else if(playerDirection == Player.RIGHT) {
            return tankRight;
        }
        else if(playerDirection == Player.DOWN) {
            return tankDown;
        }
        return tankDown;
    }

    public void getImage () {
        try {
            tankUp = ImageIO.read(getClass().getResourceAsStream("TankUp.png"));
            tankDown = ImageIO.read(getClass().getResourceAsStream("TankDown.png"));
            tankLeft = ImageIO.read(getClass().getResourceAsStream("TankLeft.png"));
            tankRight = ImageIO.read(getClass().getResourceAsStream("TankRight.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        for(int i=0 ;i<maxBlockX;i++) {
            for(int j=0;j<maxBlockY;j++) {

                int startDisplayX = player.playerPositionX - 12;
                int startDisplayY = player.playerPositionY - 9;

                if((startDisplayX + i >= minMapX && startDisplayX + i < maxMapX)&&(startDisplayY + j >= minMapY && startDisplayY + j < maxMapY)) {
                    setColor(Map.map[i+startDisplayX][j+startDisplayY], g2);
                    g2.fillRect(i * blockSize + startX,j * blockSize + startY, blockSize, blockSize);
                    if(startDisplayX + i==player.playerPositionX && startDisplayY + j==player.playerPositionY) {
                        Color grass = new Color(53, 154, 46);
                        g2.setColor(grass);
                        g2.fillRect(i * blockSize + startX,j * blockSize + startY, blockSize, blockSize);
                        g2.drawImage(convertDirectionToImage(player.playerDirection), i*blockSize + startX
                        , j*blockSize + startY, blockSize, blockSize, null);
                    }
                }
                else {
                    g2.setColor(Color.gray);
                    g2.fillRect(i * blockSize + startX,j * blockSize + startY, blockSize, blockSize);
                }
            }
        }

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Courier New", Font.BOLD, 24));
        g2.drawString("X:"+player.playerPositionX+" Y:"+player.playerPositionY
        , 5 + startX, 20*20 - 5 + startY);

        Color hp = new Color(0, 0, 0);
        if(player.playerHealth > 60) {
            hp = new Color(24, 143, 54);
        }
        else if(player.playerHealth > 30) {
            hp = new Color(212, 141, 0);
        }
        else if(player.playerHealth > 0) {
            hp = Color.RED;
        }
        g2.setColor(hp);
        g2.fillRect(5 + startX + 30, startY - 5 - 16, 200 * player.playerHealth / 100, 18);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Courier New", Font.BOLD, 24));
        g2.drawString("H:"+player.playerHealth, 5 + startX, startY - 5);

        g2.dispose();
    }

}
