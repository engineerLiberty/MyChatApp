package groupApp;

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer(){
        try{
            while (!serverSocket.isClosed()){

              Socket socket = serverSocket.accept();
                System.out.println("A new Client has connected");
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e){
            System.out.println(e);
        }

    }
    public void clientServerSocket(){
        try{
            if (serverSocket != null){
              serverSocket.close();
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] arg) throws IOException {
        ServerSocket serverSocket  = new ServerSocket(1234);
        Server server = new Server(serverSocket);
        server.startServer();

    }
}
