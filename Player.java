import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.util.Random;

public class Player extends JPanel {
    
    public int displayPositionX = 30;
    public int displayPositionY = 80;
    public int distancePlayerFromDisplayX = 12;
    public int distancePlayerFromDisplayY = 9;
    public int playerPositionX = 0;
    public int playerPositionY = 0;
    public int blockSize = 20;
    public int minX = 0;
    public int maxX = 99;
    public int minY = 0;
    public int maxY = 79;
    public BufferedImage direction;
    public MainGame game;

    public BufferedImage tankUp, tankDown, tankLeft, tankRight;

    Player (MainGame inputMainGame) {
        this.game = inputMainGame;
        getImage();
        randomSpawn();
        this.setBounds(displayPositionX + blockSize * distancePlayerFromDisplayX
        , displayPositionY + blockSize * distancePlayerFromDisplayY
        , blockSize, blockSize);
        Color grass = new Color(53, 154, 46);
        this.setBackground(grass);
        direction = tankDown;
    }

    public void randomSpawn () {
        Random rand = new Random();
        boolean spawned = false;
        while(!spawned) {
            int x = rand.nextInt(Map.maxWidth);
            int y = rand.nextInt(Map.maxHeight);

            if(Map.map[x][y]==1) {
                playerPositionX = x;
                playerPositionY = y;
                game.playerPositionX = playerPositionX;
                game.playerPositionY = playerPositionY;
                spawned = true;
            }
        }
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

    public void moveUp () {
        if(direction != tankUp) {
            direction = tankUp;
        }
        else {
            if(playerPositionY > minY) {
                playerPositionY--;
                game.playerPositionY = playerPositionY;
            }
        }
    }

    public void moveDown () {
        if(direction != tankDown) {
            direction = tankDown;
        }
        else {
            if(playerPositionY < maxY) {
                playerPositionY ++;
                game.playerPositionY = playerPositionY;
            }
        }
    }

    public void moveLeft () {
        if(direction != tankLeft) {
            direction = tankLeft;
        }
        else {
            if(playerPositionX > minX) {
                playerPositionX --;
                game.playerPositionX = playerPositionX;
            }
        }
    }

    public void moveRight () {
        if(direction != tankRight) {
            direction = tankRight;
        }
        else {
            if(playerPositionX < maxX) {
                playerPositionX ++;
                game.playerPositionX = playerPositionX;
            }
        }
    }

    public void checkOnBlock () {
        if(Map.map[playerPositionX][playerPositionY]==Map.bombBlock) {
            Result result = new Result(false);
            game.dispose();
        }
        else if(Map.map[playerPositionX][playerPositionY]==Map.batteryBlock) {
            Result result = new Result(true);
            game.dispose();
        }
    }    
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.drawImage(direction, 0, 0, blockSize, blockSize, null);
        g2.dispose();
        checkOnBlock();
        repaint();
    }
}
