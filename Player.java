// import java.awt.Color;
// import java.awt.Graphics;
// import java.awt.Graphics2D;
// import java.awt.image.BufferedImage;
// import java.io.IOException;

// import javax.imageio.ImageIO;
// import javax.swing.JPanel;

import java.util.Random;

public class Player {
    
    final public static int UP = 11;
    final public static int LEFT = 12;
    final public static int RIGHT = 13;
    final public static int DOWN = 14;

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
        // this.game = inputMainGame;
        // getImage();
        randomSpawn();
        // this.setBounds(displayPositionX + blockSize * distancePlayerFromDisplayX
        // , displayPositionY + blockSize * distancePlayerFromDisplayY
        // , blockSize, blockSize);
        // Color grass = new Color(53, 154, 46);
        // this.setBackground(grass);
        // direction = tankDown;
        this.playerHealth = 100;
        this.game = currentGame;
        Thread healThread = new Thread(new Heal(this));
        healThread.start();
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
                // game.playerPositionX = playerPositionX;
                // game.playerPositionY = playerPositionY;
                Map.map[x][y]=0;
                spawned = true;
            }
        }
    }

    // public void getImage () {
    //     try {
    //         tankUp = ImageIO.read(getClass().getResourceAsStream("TankUp.png"));
    //         tankDown = ImageIO.read(getClass().getResourceAsStream("TankDown.png"));
    //         tankLeft = ImageIO.read(getClass().getResourceAsStream("TankLeft.png"));
    //         tankRight = ImageIO.read(getClass().getResourceAsStream("TankRight.png"));
    //     }catch(IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    public boolean ifEnergy (int x, int y) {
        if(Map.map[x][y]==Map.energyTank) {
            return true;
        }
        return false;
    }

    public void moveUp () {
        if(playerDirection != UP) {
            playerDirection = UP;
        }
        else {
            if(playerPositionY > minMapY) {
                playerPositionY--;
                checkOnBlock();
                if(!ifEnergy(playerPositionX, playerPositionY)) {
                    Map.map[playerPositionX][playerPositionY] = 0;
                }
                if(!ifEnergy(playerPositionX, playerPositionY + 1)) {
                    Map.map[playerPositionX][playerPositionY + 1] = Map.grassBlock;
                }
            }
        }
    }

    public void moveDown () {
        if(playerDirection != DOWN) {
            playerDirection = DOWN;
        }
        else {
            if(playerPositionY < maxMapY - 1) {
                playerPositionY++;
                checkOnBlock();
                if(!ifEnergy(playerPositionX, playerPositionY)) {
                    Map.map[playerPositionX][playerPositionY] = 0;
                }
                if(!ifEnergy(playerPositionX, playerPositionY - 1)) {
                    Map.map[playerPositionX][playerPositionY - 1] = Map.grassBlock;
                }
            }
        }
    }

    public void moveLeft () {
        if(playerDirection != LEFT) {
            playerDirection = LEFT;
        }
        else {
            if(playerPositionX > minMapX) {
                playerPositionX --;
                checkOnBlock();
                if(!ifEnergy(playerPositionX, playerPositionY)) {
                    Map.map[playerPositionX][playerPositionY] = 0;
                }
                if(!ifEnergy(playerPositionX + 1, playerPositionY)) {
                    Map.map[playerPositionX + 1][playerPositionY] = Map.grassBlock;
                }
            }
        }
    }

    public void moveRight () {
        if(playerDirection != RIGHT) {
            playerDirection = RIGHT;
        }
        else {
            if(playerPositionX < maxMapX - 1) {
                playerPositionX++;
                checkOnBlock();
                if(!ifEnergy(playerPositionX, playerPositionY)) {
                    Map.map[playerPositionX][playerPositionY] = 0;
                }
                if(!ifEnergy(playerPositionX - 1, playerPositionY)) {
                    Map.map[playerPositionX - 1][playerPositionY] = Map.grassBlock;
                }
            }
        }
    }

    public void shooting () {
        if(!((playerPositionX == minMapX && playerDirection == Player.LEFT)
        ||(playerPositionX == maxMapX - 1 && playerDirection == Player.RIGHT)
        ||(playerPositionY == minMapY && playerDirection == Player.UP)
        ||(playerPositionY == maxMapY - 1 && playerDirection == Player.DOWN))) {
            Thread bullet = new Thread(new Bullet(playerPositionX, playerPositionY, playerDirection, this));
            bullet.start();
        }
    }

    public void checkOnBlock () {
        if(Map.map[playerPositionX][playerPositionY]==Map.bombBlock) {
            Map.destroyBomb(playerPositionX, playerPositionY);
            this.playerHealth = this.playerHealth - 5;
        }
        else if(Map.map[playerPositionX][playerPositionY]==Map.batteryBlock) {
            game.running = false;
            threadControl = false;
            Result result = new Result(true);
            game.dispose();
        }
        else if(Map.map[playerPositionX][playerPositionY]==Map.energyTank) {
            healing = true;
        }
        else if(Map.map[playerPositionX][playerPositionY]!=Map.energyTank) {
            healing = false;
        }
    }    

    public void checkHealth () {
        if(this.playerHealth == 0) {
            game.running = false;
            threadControl = false;
            Result result = new Result(false);
            game.dispose();
        }
    }
    // public void paintComponent (Graphics g) {
    //     super.paintComponent(g);

    //     Graphics2D g2 = (Graphics2D)g;

    //     g2.drawImage(direction, 0, 0, blockSize, blockSize, null);
    //     g2.dispose();
    //     checkOnBlock();
    //     repaint();
    // }
}

class Bullet implements Runnable {

    private int bulletPositionX;
    private int bulletPositionY;
    private int bulletNextPositionX;
    private int bulletNextPositionY;
    private int bulletDirection;
    public boolean exist;
    public Player targetPlayer;

    Bullet (int x, int y, int direction, Player currentPlayer) {
        this.bulletPositionX = this.bulletNextPositionX = x;
        this.bulletPositionY = this.bulletNextPositionY = y;
        this.bulletDirection = direction;
        this.exist = true;
        targetPlayer = currentPlayer;
        setBullet();
        firstCheck();
    }

    void setBullet () {
        if(this.bulletDirection==Player.UP) {
            // this.bulletPositionY--;
            this.bulletNextPositionY--;
        }
        else if(this.bulletDirection==Player.DOWN) {
            // this.bulletPositionY++;
            this.bulletNextPositionY++;
        }
        else if(this.bulletDirection==Player.LEFT) {
            // this.bulletPositionX--;
            this.bulletNextPositionX--;
        }
        else if(this.bulletDirection==Player.RIGHT) {
            // this.bulletPositionX++;
            this.bulletNextPositionX++;
        }
    }

    void firstCheck () {
        if(Map.map[bulletNextPositionX][bulletNextPositionY]==Map.bombBlock) {
            this.exist = false;
            Map.destroyBomb(bulletNextPositionX, bulletNextPositionY);
        }
    }

    void bulletMove () {
        Map.map[bulletNextPositionX][bulletNextPositionY] = Map.bullet;
        Map.map[bulletPositionX][bulletPositionY] = Map.grassBlock;
        bulletPositionX = bulletNextPositionX;
        bulletPositionY = bulletNextPositionY;
        if(this.bulletDirection==Player.UP) {
            this.bulletNextPositionY--;
        }
        else if(this.bulletDirection==Player.DOWN) {
            this.bulletNextPositionY++;
        }
        else if(this.bulletDirection==Player.LEFT) {
            this.bulletNextPositionX--;
        }
        else if(this.bulletDirection==Player.RIGHT) {
            this.bulletNextPositionX++;
        }
    }

    void ricochetOnThing () {
        boolean hitting = false;
        if(bulletNextPositionX == -1 || bulletNextPositionX == 100
        ||bulletNextPositionY == -1  || bulletNextPositionY == 80) {
            hitting = true;
        }
        else if(Map.map[bulletNextPositionX][bulletNextPositionY] == Map.bombBlock) {
            hitting = true;
            Map.destroyBomb(bulletNextPositionX, bulletNextPositionY);
        }
        else if(Map.map[bulletNextPositionX][bulletNextPositionY] == Map.energyTank) {
            hitting = true;
        }
        if(hitting) {
            dissapear();
        }
        else {
            bulletMove();
        }
    }

    void dissapear () {
        this.exist = false;
        if(Map.map[bulletPositionX][bulletPositionY] == Map.bullet) {
            Map.map[bulletPositionX][bulletPositionY] = Map.grassBlock;
        }
    }

    @Override
    public void run() {
        while(this.exist&&targetPlayer.threadControl) {
            ricochetOnThing();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}

class Heal implements Runnable {

    Player targetPlayer;

    Heal (Player inputPlayer) {
        targetPlayer = inputPlayer;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(true) {
            if(targetPlayer.healing) {
                if(Map.energyLocation[targetPlayer.playerPositionX][targetPlayer.playerPositionY].drainEnergy()) {
                    if(targetPlayer.playerHealth < 100) {
                        targetPlayer.playerHealth = targetPlayer.playerHealth + 5;
                    }
                }
            }
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
