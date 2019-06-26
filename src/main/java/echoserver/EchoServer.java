package echoserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executor;

class EchoServer {
    private final ServerSocket serverSocket;
    private final Executor executor;
    private boolean isServerRunning = true;


    EchoServer(ServerSocket serverSocket, Executor executor) {
        this.serverSocket = serverSocket;
        this.executor = executor;
    }

    void start() {
        ConsoleWriter.println(Messages.serverConnectedMessage());
        while (isServerRunning) listenForClients();
    }

    void listenForClients() {
        try {
            ConsoleWriter.println(Messages.listeningForClientsMessage());

            var clientSocket = serverSocket.accept();
            executor.execute(new ClientHandler(clientSocket));

            ConsoleWriter.println(Messages.clientConnectedMessage());
        } catch (IOException e) {
            throw new SocketOpenException(e);
        }
    }
}
