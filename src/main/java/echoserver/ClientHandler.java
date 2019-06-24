package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler extends Thread {
    public final ServerSocket serverSocket;
    private BufferedReader input;
    private PrintWriter output;
    private Socket socket;

    public ClientHandler(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        openConnection();
        sendAndReceiveMessages();
        closeConnection();
    }

    private void openConnection() {
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

    private void sendAndReceiveMessages() {
        output.println(Messages.clientInstructionsMessage());
        new MessageSender(input, output).run();
    }


    private void closeConnection() {
        try {
            input.close();
            output.close();
            System.out.println(Messages.clientDisconnectedMessage());
        } catch (IOException e) {
            throw new SocketCloseException(e);
        }
    }
}
