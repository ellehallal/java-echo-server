package echoserver;

import java.io.IOException;
import java.net.ServerSocket;

public class EchoServer {
    private final ServerSocket serverSocket;

    EchoServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void start() {
        System.out.println("Server started");
        listenForClients();
    }

    public void listenForClients() {
        try {
            var clientSocket = serverSocket.accept();
            new ClientHandler(clientSocket).run();
        } catch (IOException e) {
            throw new SocketOpenException(e);
        }
    }
}
