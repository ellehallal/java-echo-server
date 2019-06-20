package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketWrapper implements SocketWrapper {

    private final int portNumber;
    private final Display display;
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private String clientMessage = "";

    public ServerSocketWrapper(int portNumber, Display display) {
        this.portNumber = portNumber;
        this.display = display;
    }

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

            System.out.println(display.clientConnectedMessage());

        } catch (IOException e) {
            System.out.println(display.serverErrorMessage(e.getMessage()));
        }
    }

    public void receiveClientMessage() {
        try {
            clientMessage = input.readLine();
        } catch (IOException e) {
            System.out.println(display.serverErrorMessage(e.getMessage()));
        }
    }

    public void sendClientMessage() {
        output.println(display.echoFromServerMessage(clientMessage));
    }

    public void close() {
        try {
            System.out.println(display.clientDisconnectedMessage());
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            System.out.println(display.socketClosingErrorMessage(e.getMessage()));
        }
    }
}
