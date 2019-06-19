package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketWrapper extends Thread implements SocketWrapper {

    private final int portNumber;
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private String clientMessage = "";

    public ServerSocketWrapper(int portNumber) {
        this.portNumber = portNumber;
    }

    @Override
    public void run() {
        createSocketAndListen();
        while(true) {
            receiveClientMessage();
            if(clientMessage.equals(SocketAction.exit.toString())) {
                close();
                break;
            }
            sendClientMessage();
        }
    }

    public void createSocketAndListen() {
        try {
            serverSocket = new ServerSocket(portNumber);
            socket = serverSocket.accept();
            var inputStreamReader = new InputStreamReader(socket.getInputStream());
            input = new BufferedReader(inputStreamReader);
            output = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Client connected");

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    public void receiveClientMessage() {
        try {
            clientMessage = input.readLine();
        } catch (IOException e) {
            System.out.println("Error receiving message from client: " + e.getMessage());
        }
    }

    public void sendClientMessage() {
        output.println("Echo from server: " + clientMessage);
    }

    public void close() {
        try {
            System.out.println("Closing connection...");
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Socket closing error: " + e.getMessage());
        }
    }
}
