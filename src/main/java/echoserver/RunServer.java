package echoserver;

import java.io.IOException;
import java.net.ServerSocket;

public class RunServer {
    public static void main(String[] args) throws IOException {
        var serverSocket = new ServerSocket(8080);
//        var socket = new MessageSender(serverSocket.accept());
        try {
            var echoServer = new EchoServer(serverSocket);
            echoServer.openConnection();
            echoServer.run();
            echoServer.close();

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
