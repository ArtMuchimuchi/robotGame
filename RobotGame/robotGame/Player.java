// import java.awt.Color;
// import java.awt.Graphics;
// import java.awt.Graphics2D;
// import java.awt.image.BufferedImage;
// import java.io.IOException;

// import javax.imageio.ImageIO;
// import javax.swing.JPanel;

import java.util.Random;

public class Player {
    
    static final int LEFT = 37;
    static final int UP = 38;
    static final int RIGHT = 39;
    static final int DOWN = 40;
    static final int SPACEBAR = 32;

    final int minMapX = 0;
    final int maxMapX = 100;
    final int minMapY = 0;
    final int maxMapY = 80;
    // public int displayPositionX = 30;
    // public int displayPositionY = 80;
    // public int distancePlayerFromDisplayX = 12;
    // public int distancePlayerFromDisplayY = 9;
    public int playerPositionX = 0;
    public int playerPositionY = 0;
    public int playerDirection = DOWN;
    public int playerHealth;
    // public int blockSize = 20;
    // public int minX = 0;
    // public int maxX = 99;
    // public int minY = 0;
    // public int maxY = 79;
    // public BufferedImage direction;
    public MainGame game;
    public boolean threadControl = true;
    public boolean healing = false;
    public String playerName;

    // public BufferedImage tankUp, tankDown, tankLeft, tankRight;

    Player (MainGame currentGame) {
        this.game = currentGame;
        updatePlayerData();
    }

    public void updatePlayerData () {
        this.playerPositionX = game.client.player.playerPositionX;
        this.playerPositionY = game.client.player.playerPositionY;
        this.playerDirection = game.client.player.playerDirection;
        this.playerHealth = game.client.player.playerHealth;
    }

    public void moveUp () {
        game.client.setMessage("Up");
    }

    public void moveDown () {
        game.client.setMessage("Down");
    }

    public void moveLeft () {
        game.client.setMessage("Left");
    }

    public void moveRight () {
        game.client.setMessage("Right");
    }

    public void shooting () {
        game.client.setMessage("Shoot");
    }
}
