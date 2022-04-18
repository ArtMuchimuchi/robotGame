import java.io.Serializable;

public class packMap implements Serializable {

    static public final int maxWidth = 100;
    static public final int maxHeight = 80;

    public int map[][] = new int[maxWidth][maxHeight];
    public int mapPlayerPosition[][] = new int[maxWidth][maxHeight];
    public int mapPlayerID[][] = new int[maxWidth][maxHeight];
    public EnergyTank energyLocation[][] = new EnergyTank[maxWidth][maxHeight];
    public Bullet bulletLocation[][] = new Bullet[maxWidth][maxHeight];
    

    packMap(Map inputMap) {
        this.map = inputMap.map;
        this.mapPlayerPosition = inputMap.mapPlayerPosition;
        this.mapPlayerID = inputMap.mapPlayerID;
        this.energyLocation = inputMap.energyLocation;
        this.bulletLocation = inputMap.bulletLocation;
    }

}
