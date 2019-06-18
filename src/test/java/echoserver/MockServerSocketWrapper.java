package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MockServerSocketWrapper extends Thread implements SocketWrapper {

    private final BufferedReader input;
    private final PrintWriter output;
    private boolean isCreateSocketAndListenCalled = false;
    private String receivedClientMessage;

    public MockServerSocketWrapper(BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void run() {
        createSocketAndListen();
        receiveClientMessage();
    }

    public void createSocketAndListen() {
        this.isCreateSocketAndListenCalled = true;
    }

    public void receiveClientMessage() {
        try {
            receivedClientMessage = input.readLine();
        } catch (IOException e) {
            System.out.println("Error receiving message: " + e.getMessage());
        }

    }

    public void sendClientMessage(String message) {

    }

    public void close() {

    }

    public boolean isCreateSocketAndListenCalled() {
        return isCreateSocketAndListenCalled;
    }

    public String getReceivedClientMessage() {
        return receivedClientMessage;
    }
}
