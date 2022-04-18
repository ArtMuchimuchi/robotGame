import java.io.Serializable;
import java.util.Random;

public class Map implements Serializable{

    static final int LEFT = 37;
    static final int UP = 38;
    static final int RIGHT = 39;
    static final int DOWN = 40;
    static final int SPACEBAR = 32;
    
    static public final int maxWidth = 100;
    static public final int maxHeight = 80;

    final int minMapX = 0;
    final int maxMapX = 100;
    final int minMapY = 0;
    final int maxMapY = 80;

    static public final int bombRate = 3;
    static public final int numEnergyTank = 25;

    static public final int grassBlock = 1;
    static public final int bombBlock = 2;
    static public final int batteryBlock = 3;
    static public final int bullet = 4;
    static public final int energyTank = 5;

    public int map[][] = new int[maxWidth][maxHeight];
    public int mapPlayerPosition[][] = new int[maxWidth][maxHeight];
    public int mapPlayerID[][] = new int[maxWidth][maxHeight];
    public EnergyTank energyLocation[][] = new EnergyTank[maxWidth][maxHeight];
    public Bullet bulletLocation[][] = new Bullet[maxWidth][maxHeight];

    Map () {
        initiateMap();
        placeBomb(calculateNum(maxWidth, maxHeight, bombRate), maxWidth, maxHeight);
        placeEnergyTank(numEnergyTank, maxWidth, maxHeight);
    }

    public void initiateMap () {
        for(int i=0;i<maxWidth;i++) {
            for(int j=0;j<maxHeight;j++) {
                map[i][j] = grassBlock;
                energyLocation[i][j] = null;
                bulletLocation[i][j] = null;
                mapPlayerPosition[i][j] = 0;
                mapPlayerID[i][j] = 0;
            }
        }
    }

    public void placeBomb (int numBomb, int column, int row) {
        Random rand = new Random();
        int bombPlaced = 0;
        int i;
        int j;
        while(bombPlaced < numBomb) {
            i = rand.nextInt(column);
            j = rand.nextInt(row);
            if(map[i][j]==grassBlock) {
                map[i][j]=bombBlock;
                bombPlaced++;
            }
        }
    }

    public void destroyBomb (int positionX, int positionY) {
        Random rand = new Random();
        int bombPlaced = 0;
        int i;
        int j;
        while(bombPlaced < 1) {
            i = rand.nextInt(maxWidth);
            j = rand.nextInt(maxHeight);
            if(map[i][j]==grassBlock) {
                map[i][j]=bombBlock;
                bombPlaced++;
            }
        }
        map[positionX][positionY] = grassBlock;
    }

    public void placeEnergyTank (int num, int column, int row) {
        Random rand = new Random();
        int energyTankPlaced = 0;
        int i;
        int j;
        while(energyTankPlaced < num) {
            i = rand.nextInt(column);
            j = rand.nextInt(row);
            if(map[i][j]==grassBlock) {
                EnergyTank energyBlock = new EnergyTank(i, j, this);
                energyTankPlaced++;
            }
        }
    }

    public int calculateNum (int width, int height, int rate) {
        return (width * height) * rate / 100 ;
    }

    public void printMap() {
        int i,j;
        for(i=0;i<maxWidth;i++) {
            for(j=0;j<maxHeight;j++) {
                System.out.println(map[i][j]);
            }
        }
    }

    public synchronized void playerRandomSpawn (PackagePlayerData player) {
        Random rand = new Random();
        boolean spawned = false;
        while(!spawned) {
            int x = rand.nextInt(Map.maxWidth);
            int y = rand.nextInt(Map.maxHeight);

            if(map[x][y]==Map.grassBlock) {
                player.playerPositionX = x;
                player.playerPositionY = y;
                mapPlayerPosition[player.playerPositionX][player.playerPositionY] = player.playerDirection;
                mapPlayerID[player.playerPositionX][player.playerPositionY] = player.playerID;
                spawned = true;
            }
        }
    }

    public synchronized void playerMoveUp (PackagePlayerData player) {
        if(player.playerDirection != UP) {
            player.playerDirection = UP;
            mapPlayerPosition[player.playerPositionX][player.playerPositionY] = UP;
        }
        else {
            if(player.playerPositionY > minMapY && mapPlayerPosition[player.playerPositionX][player.playerPositionY - 1] == 0) {
                player.playerPositionY--;
                checkOnBlock(player);
                mapPlayerPosition[player.playerPositionX][player.playerPositionY] = UP;
                mapPlayerID[player.playerPositionX][player.playerPositionY] = player.playerID;
                mapPlayerPosition[player.playerPositionX][player.playerPositionY + 1] = 0;
                mapPlayerID[player.playerPositionX][player.playerPositionY + 1] = 0;

            }
        }
    }

    public synchronized void playerMoveDown (PackagePlayerData player) {
        if(player.playerDirection != DOWN) {
            player.playerDirection = DOWN;
            mapPlayerPosition[player.playerPositionX][player.playerPositionY] = DOWN;
        }
        else {
            if(player.playerPositionY < maxMapY - 1 && mapPlayerPosition[player.playerPositionX][player.playerPositionY + 1] == 0) {
                player.playerPositionY++;
                checkOnBlock(player);
                mapPlayerPosition[player.playerPositionX][player.playerPositionY] = DOWN;
                mapPlayerID[player.playerPositionX][player.playerPositionY] = player.playerID;
                mapPlayerPosition[player.playerPositionX][player.playerPositionY - 1] = 0;
                mapPlayerID[player.playerPositionX][player.playerPositionY - 1] = 0;
            }
        }
    }

    public synchronized void playerMoveLeft (PackagePlayerData player) {
        if(player.playerDirection != LEFT) {
            player.playerDirection = LEFT;
            mapPlayerPosition[player.playerPositionX][player.playerPositionY] = LEFT;
        }
        else {
            if(player.playerPositionX > minMapX && mapPlayerPosition[player.playerPositionX - 1][player.playerPositionY] == 0) {
                player.playerPositionX --;
                checkOnBlock(player);
                mapPlayerPosition[player.playerPositionX][player.playerPositionY] = LEFT;
                mapPlayerID[player.playerPositionX][player.playerPositionY] = player.playerID;
                mapPlayerPosition[player.playerPositionX + 1][player.playerPositionY] = 0;
                mapPlayerID[player.playerPositionX + 1][player.playerPositionY] = 0;
            }
        }
    }

    public synchronized void playerMoveRight (PackagePlayerData player) {
        if(player.playerDirection != RIGHT) {
            player.playerDirection = RIGHT;
            mapPlayerPosition[player.playerPositionX][player.playerPositionY] = RIGHT;
        }
        else {
            if(player.playerPositionX < maxMapX - 1 && mapPlayerPosition[player.playerPositionX + 1][player.playerPositionY] == 0) {
                player.playerPositionX++;
                checkOnBlock(player);
                mapPlayerPosition[player.playerPositionX][player.playerPositionY] = RIGHT;
                mapPlayerID[player.playerPositionX][player.playerPositionY] = player.playerID;
                mapPlayerPosition[player.playerPositionX - 1][player.playerPositionY] = 0;
                mapPlayerID[player.playerPositionX - 1][player.playerPositionY] = 0;
            }
        }
    }

    public void checkOnBlock (PackagePlayerData player) {
        if(map[player.playerPositionX][player.playerPositionY]==Map.bombBlock) {
            destroyBomb(player.playerPositionX, player.playerPositionY);
            player.playerHealth = player.playerHealth - 5;
        }
        else if(map[player.playerPositionX][player.playerPositionY]==Map.energyTank) {
            player.healing = true;
        }
        else if(map[player.playerPositionX][player.playerPositionY]!=Map.energyTank) {
            player.healing = false;
        }
    }
    
    public void playerShooting (PackagePlayerData player) {
        if(!((player.playerPositionX == minMapX && player.playerDirection == Player.LEFT)
        ||(player.playerPositionX == maxMapX - 1 && player.playerDirection == Player.RIGHT)
        ||(player.playerPositionY == minMapY && player.playerDirection == Player.UP)
        ||(player.playerPositionY == maxMapY - 1 && player.playerDirection == Player.DOWN))) {
            Thread bullet = new Thread(new Bullet(player.playerPositionX, player.playerPositionY, player.playerDirection, this));
            bullet.start();
        }
    }
}

class EnergyTank implements Serializable {
    public Map map;
    public int positionX;
    public int positionY;
    public int energy;

    EnergyTank (int x, int y, Map inputMap) {
        this.map = inputMap;
        this.positionX = x;
        this.positionY = y;
        map.map[x][y] = Map.energyTank;
        Random rand = new Random();
        int energyMultiply = rand.nextInt(10);
        energy = 50 + 5 * energyMultiply;
        map.energyLocation[x][y] = this;
    }

    public boolean drainEnergy () {
        if(energy==0) {
            dissapear();
            return false;
        }
        else if(energy==5) {
            energy = energy - 5;
            dissapear();
            return true;
        }
        else {
            energy = energy - 5;
            return true;
        }
    }

    public void dissapear () {
        Random rand = new Random();
        int energyTankPlaced = 0;
        int i;
        int j;
        while(energyTankPlaced < 1) {
            i = rand.nextInt(Map.maxWidth);
            j = rand.nextInt(Map.maxHeight);
            if(map.map[i][j]==Map.grassBlock) {
                EnergyTank energyBlock = new EnergyTank(i, j, map);
                energyTankPlaced++;
            }
        }
        map.map[positionX][positionY] = Map.grassBlock;
        map.energyLocation[positionX][positionY] = null;
    }
}

class Bullet implements Runnable, Serializable {

    private int bulletPositionX;
    private int bulletPositionY;
    private int bulletNextPositionX;
    private int bulletNextPositionY;
    private int bulletDirection;
    private Map map;
    public boolean exist;
    
    Bullet (int x, int y, int direction, Map inputMap) {
        this.bulletPositionX = this.bulletNextPositionX = x;
        this.bulletPositionY = this.bulletNextPositionY = y;
        this.bulletDirection = direction;
        this.exist = true;
        this.map = inputMap;
        setBullet();
        firstCheck();
    }
    
    synchronized void setBullet () {
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
    
    synchronized void firstCheck () {
        if(map.map[bulletNextPositionX][bulletNextPositionY]==Map.bombBlock) {
            this.exist = false;
            map.destroyBomb(bulletNextPositionX, bulletNextPositionY);
        }
    }
    
    synchronized void bulletMove () {
        map.bulletLocation[bulletNextPositionX][bulletNextPositionY] = this;
        if(map.bulletLocation[bulletPositionX][bulletPositionY] == this) {
            map.bulletLocation[bulletPositionX][bulletPositionY] = null;
        }
        bulletPositionX = bulletNextPositionX;
        bulletPositionY = bulletNextPositionY;
        if(this.bulletDirection==Map.UP) {
            this.bulletNextPositionY--;
        }
        else if(this.bulletDirection==Map.DOWN) {
            this.bulletNextPositionY++;
        }
        else if(this.bulletDirection==Map.LEFT) {
            this.bulletNextPositionX--;
        }
        else if(this.bulletDirection==Map.RIGHT) {
            this.bulletNextPositionX++;
        }
    }
    
    synchronized void ricochetOnThing () {
        boolean hitting = false;
        if(bulletNextPositionX == -1 || bulletNextPositionX == 100
        ||bulletNextPositionY == -1  || bulletNextPositionY == 80) {
            hitting = true;
        }
        else if(map.map[bulletNextPositionX][bulletNextPositionY] == Map.bombBlock) {
            hitting = true;
            map.destroyBomb(bulletNextPositionX, bulletNextPositionY);
        }
        else if(map.map[bulletNextPositionX][bulletNextPositionY] == Map.energyTank) {
            hitting = true;
        }
        if(hitting) {
            dissapear();
        }
        else {
            bulletMove();
        }
    }
    
    synchronized void dissapear () {
        this.exist = false;
        if(map.bulletLocation[bulletPositionX][bulletPositionY] != null) {
            map.bulletLocation[bulletPositionX][bulletPositionY] = null;
        }
    }
    
    @Override
    public void run() {
        while(this.exist) {
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
