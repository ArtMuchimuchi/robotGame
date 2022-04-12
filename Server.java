import java.io.*;
import java.net.*;
  
// Server class
class Server {
    public static void main(String[] args)
    {
        ServerSocket server = null;
        Map serverMap = new Map();
  
        try {
  
            server = new ServerSocket(3344);
            server.setReuseAddress(true);
  
            while (true) {

                Socket client = server.accept();

                System.out.println("New client connected"
                                   + client.getInetAddress()
                                         .getHostAddress());
  
                ClientHandler clientSock
                    = new ClientHandler(client, serverMap);
  
                new Thread(clientSock).start();
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

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        private Map map;
        private ObjectOutputStream out;
        private ObjectInputStream in;
        private String message;

        public ClientHandler(Socket socket, Map targetMap)
        {
            this.clientSocket = socket;
            this.map = targetMap;
            try {
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                in = new ObjectInputStream(clientSocket.getInputStream());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
  
        public void run()
        {
            while(true) {
                try {
                    out.writeObject(map);
                    MessagePack inputMessage = (MessagePack) in.readObject();
                    message = inputMessage.readMessage();
                    if(message!=null) {
                        System.out.print(message);
                        message = null;
                    }
                }
                catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}