import java.util.Random;

public class Map {
    
    static final int maxWidth = 100;
    static final int maxHeight = 80;

    static final int bombRate = 3;
    static final int numEnergyTank = 25;

    static final int grassBlock = 1;
    static final int bombBlock = 2;
    static final int batteryBlock = 3;
    static final int bullet = 4;
    static final int energyTank = 5;

    static public int map[][] = new int[maxWidth][maxHeight];
    static public EnergyTank energyLocation[][] = new EnergyTank[maxWidth][maxHeight];

    Map () {
        initiateMap();
        placeBomb(calculateNum(maxWidth, maxHeight, bombRate), maxWidth, maxHeight);
        placeBattery(maxWidth, maxHeight);
        placeEnergyTank(numEnergyTank, maxWidth, maxHeight);
    }

    public void initiateMap () {
        for(int i=0;i<maxWidth;i++) {
            for(int j=0;j<maxHeight;j++) {
                map[i][j] = grassBlock;
                energyLocation[i][j] = null;
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

    public static void destroyBomb (int positionX, int positionY) {
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
                EnergyTank energyBlock = new EnergyTank(i, j);
                energyTankPlaced++;
            }
        }
    }

    public void placeBattery (int column, int row) {
        Random rand = new Random();
        int batteryPlaced = 0;
        int i;
        int j;
        while(batteryPlaced < 1) {
            i = rand.nextInt(column);
            j = rand.nextInt(row);
            if(map[i][j]==grassBlock) {
                map[i][j]=batteryBlock;
                batteryPlaced++;
            }
        }
    }

    public int calculateNum (int width, int height, int rate) {
        return (width * height) * rate / 100 ;
    }
    
}

class EnergyTank {
    public int positionX;
    public int positionY;
    public int energy;

    EnergyTank (int x, int y) {
        this.positionX = x;
        this.positionY = y;
        Map.map[x][y] = Map.energyTank;
        Random rand = new Random();
        int energyMultiply = rand.nextInt(10);
        energy = 50 + 5 * energyMultiply;
        Map.energyLocation[x][y] = this;
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
            if(Map.map[i][j]==Map.grassBlock) {
                EnergyTank energyBlock = new EnergyTank(i, j);
                energyTankPlaced++;
            }
        }
        Map.map[positionX][positionY] = Map.grassBlock;
        Map.energyLocation[positionX][positionY] = null;
    }
}
