import java.util.Random;

public class Map {
    
    static final int maxWidth = 100;
    static final int maxHeight = 80;

    static final int bombRate = 3;

    static final int grassBlock = 1;
    static final int bombBlock = 2;
    static final int batteryBlock = 3;
    static final int bullet = 4;

    static public int map[][] = new int[maxWidth][maxHeight];

    Map () {
        initiateMap();
        placeBomb(calculateNumBomb(maxWidth, maxHeight, bombRate), maxWidth, maxHeight);
        placeBattery(maxWidth, maxHeight);
    }

    public void initiateMap () {
        for(int i=0;i<maxWidth;i++) {
            for(int j=0;j<maxHeight;j++) {
                map[i][j] = grassBlock;
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

    public int calculateNumBomb (int width, int height, int bombRate) {
        return (width * height) * bombRate / 100 ;
    }
    
}
