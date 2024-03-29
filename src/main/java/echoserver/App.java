package echoserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws IOException {
        var serverSocket = new ServerSocket(8080);

        try {
            var executor = Executors.newCachedThreadPool();
            new EchoServer(serverSocket, executor).start();

        } catch (Exception e) {
            if (e instanceof SocketCloseException) {
                ConsoleWriter.println
                        (Messages.socketClosingErrorMessage(e.getMessage()));
            }
            else if (e instanceof SocketOpenException) {
                ConsoleWriter.println
                        (Messages.socketOpeningErrorMessage(e.getMessage()));
            }
        }
    }
}
