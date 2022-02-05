import java.util.Random;

public class Map {
    
    static final int maxWidth = 100;
    static final int maxHeight = 80;

    static public int map[][] = new int[maxWidth][maxHeight];

    Map () {

        initiateMap();
        placeBomb(560, maxWidth, maxHeight);
        placeBattery(maxWidth, maxHeight);
        
    }

    public void initiateMap () {
        for(int i=0;i<maxWidth;i++) {
            for(int j=0;j<maxHeight;j++) {
                map[i][j] = 1;
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
            if(map[i][j]!=2) {
                map[i][j]=2;
                bombPlaced++;
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
            if(map[i][j]!=3&&map[i][j]!=2) {
                map[i][j]=3;
                batteryPlaced++;
            }
        }
    }
    
    static public void printMap () {
        for(int i=0;i<maxWidth;i++) {
            for(int j=0;j<maxHeight;j++) {
                System.out.println(map[i][j]);
            }
        }
    }

}
