package echoserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class EchoServer {
    private final ServerSocket serverSocket;
    private final ExecutorService executor;
    private final ArrayList<Socket> clientSockets = new ArrayList<>();


    EchoServer(ServerSocket serverSocket, ExecutorService executor) {
        this.serverSocket = serverSocket;
        this.executor = executor;
    }

    void start() {
        ConsoleWriter.println(Messages.serverConnectedMessage());
        while (true) listenForClients();
    }

    void listenForClients() {
        try {
            ConsoleWriter.println(Messages.listeningForClientsMessage());

            var clientSocket = serverSocket.accept();
            executor.execute(new ClientHandler(clientSocket));
            clientSockets.add(clientSocket);

            ConsoleWriter.println(Messages.clientConnectedMessage());
        } catch (IOException e) {
            throw new SocketOpenException(e);
        }
    }

    List<Socket> getClientSockets() {
        return clientSockets;
    }
}
