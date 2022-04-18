import java.io.*;
import java.lang.reflect.Array;
import java.net.*;
import java.util.ArrayList;

public class Server {

    public Map serverMap = new Map();
    private ArrayList<ClientHandler> connectionList;
    private ServerSocket server;
    private boolean done;
    private int id;

    Server () {

        connectionList = new ArrayList<>();
        done = false;
        id=0;
        try {
  
            server = new ServerSocket(3344);
            server.setReuseAddress(true);
  
            while (!done) {

                Socket client = server.accept();

                System.out.println("New client connected"
                                   + client.getInetAddress()
                                         .getHostAddress());
                
                id++;

                ClientHandler clientSock
                    = new ClientHandler(client, this, id);
  
                new Thread(clientSock).start();
                connectionList.add(clientSock);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (server != null) {
                try {
                    server.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args)
    {
        Server server = new Server();
    }

    public void closeServer () {
        done = true;
        if(!server.isClosed()) {
            // try {
                for(ClientHandler ch: connectionList) {
                    ch.player.done = true;
                    // ch.shutdown();
                }
                // server.close();
                
            // } catch (IOException e) {
            //     // TODO Auto-generated catch block
            //     e.printStackTrace();
            // }
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        private Server server;
        private ObjectOutputStream out;
        private ObjectInputStream in;
        private String message;
        private int id;
        public PackagePlayerData player;

        public ClientHandler(Socket socket, Server inputServer, int inputId)
        {
            this.clientSocket = socket;
            this.server = inputServer;
            this.id = inputId;
            player = new PackagePlayerData();
            player.setUp();
            player.playerID = id;
            server.serverMap.playerRandomSpawn(player);
            Thread healThread = new Thread(new Heal(player, server));
            healThread.start();
            try {
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                in = new ObjectInputStream(clientSocket.getInputStream());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public void shutdown() {
            try {
                packMap packageMap = new packMap(server.serverMap);
                out.reset();
                out.writeObject(packageMap);
                out.reset();
                out.writeObject(player);
                in.close();
                out.close();
                if(!clientSocket.isClosed()) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
  
        public void run()
        {
            while(!player.done) {
                try {
                    if(player.playerHealth == 0) {
                        player.done = true;
                        server.closeServer();
                    }
                    packMap packageMap = new packMap(server.serverMap);
                    out.reset();
                    out.writeObject(packageMap);
                    out.reset();
                    out.writeObject(player);
                    MessagePack inputMessage = (MessagePack) in.readObject();
                    message = inputMessage.readMessage();
                    if(message!=null) {
                        if(message.equals("Up")) {
                            server.serverMap.playerMoveUp(player);                
                        }
                        else if(message.equals("Down")){
                            server.serverMap.playerMoveDown(player);
                        }
                        else if(message.equals("Left")){
                            server.serverMap.playerMoveLeft(player);
                        }
                        else if(message.equals("Right")){
                            server.serverMap.playerMoveRight(player);
                        }
                        else if(message.equals("Shoot")){
                            server.serverMap.playerShooting(player);
                        }
                        else {
                            System.out.print("Error!");
                        }
                        message = null;
                    }
                }
                catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            try {
                packMap packageMap = new packMap(server.serverMap);
                out.reset();
                out.writeObject(packageMap);
                out.reset();
                out.writeObject(player);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

class Heal implements Runnable {

    PackagePlayerData targetPlayer;
    Server server;

    Heal (PackagePlayerData inputPlayer, Server inputServer) {
        targetPlayer = inputPlayer;
        this.server = inputServer;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(true) {
            if(targetPlayer.healing) {
                if(targetPlayer.playerHealth < 100) {
                    if(server.serverMap.energyLocation[targetPlayer.playerPositionX][targetPlayer.playerPositionY].drainEnergy()) {
                        targetPlayer.playerHealth = targetPlayer.playerHealth + 5;
                    }
                }
            }
            if(server.serverMap.bulletLocation[targetPlayer.playerPositionX][targetPlayer.playerPositionY]!=null) {
                server.serverMap.bulletLocation[targetPlayer.playerPositionX][targetPlayer.playerPositionY].dissapear();
                if(targetPlayer.playerHealth > 50) {
                    targetPlayer.playerHealth = targetPlayer.playerHealth - 50;
                }
                else {
                    targetPlayer.playerHealth = 0;
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