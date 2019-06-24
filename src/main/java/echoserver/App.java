package echoserver;

import java.io.IOException;
import java.net.ServerSocket;

public class App {
    public static void main(String[] args) throws IOException {
        var serverSocket = new ServerSocket(8080);

        try {
            new EchoServer(serverSocket).start();

        } catch (Exception e) {
            if (e instanceof SocketCloseException) {
                System.out.println
                        (Messages.socketClosingErrorMessage(e.getMessage()));
            }
            else if (e instanceof SocketOpenException) {
                System.out.println
                        (Messages.socketOpeningErrorMessage(e.getMessage()));
            }
        }
    }
}
