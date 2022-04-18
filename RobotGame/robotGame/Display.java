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

    public BufferedImage tankUp[] = new BufferedImage[4];
    public BufferedImage tankDown[] = new BufferedImage[4];
    public BufferedImage tankLeft[] = new BufferedImage[4];
    public BufferedImage tankRight[] = new BufferedImage[4];

    Player player;
    MainGame game;

    public Display (Player targetPlayer, MainGame inputGame) {
        this.setBounds(0, 0, MainGame.windowsWidth, MainGame.windowsHeight);
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.player = targetPlayer;
        this.game = inputGame;
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
        else if(blockValue == Map.energyTank) {
            Color energy = Color.RED;
            g2.setColor(energy);
        }
    }

    public BufferedImage convertDirectionToImage (int playerDirection, int id) {
        int pictureID = id%4;
        if(playerDirection == Player.UP) {
            return tankUp[pictureID];
        }
        else if(playerDirection == Player.LEFT) {
            return tankLeft[pictureID];
        }
        else if(playerDirection == Player.RIGHT) {
            return tankRight[pictureID];
        }
        else if(playerDirection == Player.DOWN) {
            return tankDown[pictureID];
        }
        return tankDown[pictureID];
    }

    public void getImage () {
        try {
            tankUp[0] = ImageIO.read(getClass().getResourceAsStream("/Pictures/player1/TankUp.png"));
            tankDown[0] = ImageIO.read(getClass().getResourceAsStream("/Pictures/player1/TankDown.png"));
            tankLeft[0] = ImageIO.read(getClass().getResourceAsStream("/Pictures/player1/TankLeft.png"));
            tankRight[0] = ImageIO.read(getClass().getResourceAsStream("/Pictures/player1/TankRight.png"));
            tankUp[1] = ImageIO.read(getClass().getResourceAsStream("/Pictures/player2/TankUp.png"));
            tankDown[1] = ImageIO.read(getClass().getResourceAsStream("/Pictures/player2/TankDown.png"));
            tankLeft[1] = ImageIO.read(getClass().getResourceAsStream("/Pictures/player2/TankLeft.png"));
            tankRight[1] = ImageIO.read(getClass().getResourceAsStream("/Pictures/player2/TankRight.png"));
            tankUp[2] = ImageIO.read(getClass().getResourceAsStream("/Pictures/player3/TankUp.png"));
            tankDown[2] = ImageIO.read(getClass().getResourceAsStream("/Pictures/player3/TankDown.png"));
            tankLeft[2] = ImageIO.read(getClass().getResourceAsStream("/Pictures/player3/TankLeft.png"));
            tankRight[2] = ImageIO.read(getClass().getResourceAsStream("/Pictures/player3/TankRight.png"));
            tankUp[3] = ImageIO.read(getClass().getResourceAsStream("/Pictures/player4/TankUp.png"));
            tankDown[3] = ImageIO.read(getClass().getResourceAsStream("/Pictures/player4/TankDown.png"));
            tankLeft[3] = ImageIO.read(getClass().getResourceAsStream("/Pictures/player4/TankLeft.png"));
            tankRight[3] = ImageIO.read(getClass().getResourceAsStream("/Pictures/player4/TankRight.png"));
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
                    setColor(game.client.map.map[i+startDisplayX][j+startDisplayY], g2);
                    g2.fillRect(i * blockSize + startX,j * blockSize + startY, blockSize, blockSize);
                    if(game.client.map.map[i+startDisplayX][j+startDisplayY]==Map.energyTank) {
                        Color energy = new Color(0, 255, 255);
                        g2.setColor(energy);
                        g2.fillRect(i * blockSize + 2 + startX,j * blockSize + 2 + startY, blockSize - 4, blockSize - 4);
                        g2.setColor(Color.black);
                        g2.drawString(String.valueOf(game.client.map.energyLocation[i+startDisplayX][j+startDisplayY].energy)
                        , i * blockSize + startX + 4,j * blockSize + startY + 14);
                    }
                    if(game.client.map.bulletLocation[i+startDisplayX][j+startDisplayY]!= null) {
                        Color bullet = new Color(0, 255, 255);
                        g2.setColor(bullet);
                        g2.fillRect(i * blockSize + startX,j * blockSize + startY, blockSize, blockSize);
                    }
                    if(game.client.map.mapPlayerPosition[i+startDisplayX][j+startDisplayY]!=0) {
                        g2.drawImage(convertDirectionToImage(game.client.map.mapPlayerPosition[i+startDisplayX][j+startDisplayY], 
                        game.client.map.mapPlayerID[i+startDisplayX][j+startDisplayY]),
                        i*blockSize + startX, j*blockSize + startY, blockSize, blockSize, null);
                    
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

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Courier New", Font.BOLD, 24));
        g2.drawString("Player : "+player.playerName, 5 + startX, startY - 5 - 30);

        g2.dispose();
    }

}
