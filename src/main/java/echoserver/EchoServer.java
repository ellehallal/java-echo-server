package echoserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class EchoServer {
    private final ServerSocket serverSocket;
    private ExecutorService executor;
    private ArrayList<Socket> clientSockets = new ArrayList<>();


    EchoServer(ServerSocket serverSocket, ExecutorService executor) {
        this.serverSocket = serverSocket;
        this.executor = executor;
    }

    void start() {
        while (true) listenForClients();
    }

    void listenForClients() {
        try {
            System.out.println(Messages.listeningForClientsMessage());

            var clientSocket = serverSocket.accept();
            executor.execute(new ClientHandler(clientSocket));
            clientSockets.add(clientSocket);

            System.out.println(Messages.clientConnectedMessage());
        } catch (IOException e) {
            throw new SocketOpenException(e);
        }
    }

    List<Socket> getClientSockets() {
        return clientSockets;
    }
}
