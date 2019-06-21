package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public final ServerSocket serverSocket;
    private BufferedReader input;
    private PrintWriter output;
    private Socket socket;

    public EchoServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void openConnection() {
        try {
            socket = serverSocket.accept();
            var inputStreamReader = new InputStreamReader(socket.getInputStream());
            input = new BufferedReader(inputStreamReader);
            output = new PrintWriter(socket.getOutputStream(), true);
            System.out.println(Messages.clientConnectedMessage());
        } catch (IOException e) {
            throw new SocketOpenException(e);
        }
    }

    public void run() {
        new MessageSender(input, output).run();
    }


    public void close() {
        try {
            input.close();
            output.close();
            System.out.println(Messages.clientDisconnectedMessage());
        } catch (IOException e) {
            throw new SocketCloseException(e);
        }
    }
}
