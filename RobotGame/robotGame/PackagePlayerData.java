import java.io.Serializable;

public class PackagePlayerData implements Serializable {

    static final int LEFT = 37;
    static final int UP = 38;
    static final int RIGHT = 39;
    static final int DOWN = 40;
    static final int SPACEBAR = 32;

    public int playerID;
    public int playerPositionX;
    public int playerPositionY;
    public int playerDirection;
    public int playerHealth;
    public boolean healing;
    public boolean done;

    PackagePlayerData() {
        
    }

    PackagePlayerData(PlayerHandler targetPlayer) {
       this.playerPositionX = targetPlayer.playerPositionX;
       this.playerPositionY = targetPlayer.playerPositionY;
       this.playerDirection = targetPlayer.playerDirection;
       this.playerHealth = targetPlayer.playerHealth;
    }
    
    void setUp() {
        this.playerHealth = 100;
        this.playerDirection = DOWN;
        this.healing = false;
        this.done = false;
    }
}
