package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MockServerSocketWrapper extends Thread implements SocketWrapper {

    private final BufferedReader input;
    private final PrintWriter output;
    private boolean isCreateSocketAndListenCalled = false;
    private String receivedMessage;

    public MockServerSocketWrapper(BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void run() {
        createSocketAndListen();
        receiveMessage();
    }

    public void createSocketAndListen() {
        this.isCreateSocketAndListenCalled = true;
    }

    public void receiveMessage() {
        try {
            receivedMessage = input.readLine();
        } catch (IOException e) {
            System.out.println("Error receiving message: " + e.getMessage());
        }

    }

    public void sendMessage(String message) {

    }

    public void close() {

    }

    public boolean isCreateSocketAndListenCalled() {
        return isCreateSocketAndListenCalled;
    }

    public String getReceivedMessage() {
        return receivedMessage;
    }
}
