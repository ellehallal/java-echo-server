package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        setInputAndOutput();
        sendAndReceiveMessages();
        closeConnection();
    }

    private void setInputAndOutput() {
        try {
            var inputStreamReader = new InputStreamReader(socket.getInputStream());
            input = new BufferedReader(inputStreamReader);
            output = new PrintWriter(socket.getOutputStream(), true);
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
            socket.close();
            System.out.println(Messages.clientDisconnectedMessage());
        } catch (IOException e) {
            throw new SocketCloseException(e);
        }
    }
}
