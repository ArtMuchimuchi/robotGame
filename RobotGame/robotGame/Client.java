import java.io.*;
import java.net.*;
import java.util.*;
  
// Client class
class Client implements Runnable {
    
    public Socket socket;
    public ObjectOutputStream out;
    public ObjectInputStream in;
    public Map map;
    public String message;
    public PackagePlayerData player;
    public int id;

    // driver code
    public Client () 
    {
        try {
            socket = new Socket("localhost", 3344);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            message = null;
            map = new Map();
            player = new PackagePlayerData();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void setMessage (String a) {
        message = a;
    }

    @Override
    public void run() {
        while(!player.done) {
            try {
                packMap packageMap = new packMap(map);
                packageMap = (packMap) in.readObject();
                map.map = packageMap.map;
                map.mapPlayerPosition = packageMap.mapPlayerPosition;
                map.mapPlayerID = packageMap.mapPlayerID;
                map.energyLocation = packageMap.energyLocation;
                map.bulletLocation = packageMap.bulletLocation;
                player = (PackagePlayerData) in.readObject();
                id = player.playerID;
                MessagePack messagePack = new MessagePack();
                if(message!=null) {
                    messagePack.setMessage(message);
                    message = null;
                }
                out.writeObject(messagePack);
            }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    
}