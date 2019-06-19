package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MockServerSocketWrapper extends Thread implements SocketWrapper {

    private final BufferedReader input;
    private final PrintWriter output;
    private boolean isCreateSocketAndListenCalled = false;
    private String receivedClientMessage;
    private boolean isCloseCalled = false;

    public MockServerSocketWrapper(BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void run() {
        createSocketAndListen();
        while(true) {
            receiveClientMessage();
            if(receivedClientMessage.equals(SocketAction.exit.toString())) {
                close();
                break;
            }
            sendClientMessage();
            break;
        }
    }

    public void createSocketAndListen() {
        isCreateSocketAndListenCalled = true;
    }

    public void receiveClientMessage() {
        try {
            receivedClientMessage = input.readLine();
        } catch (IOException e) {
            System.out.println("Error receiving message: " + e.getMessage());
        }
    }

    public void sendClientMessage() {
        System.out.println("Echo from server: " + receivedClientMessage);
    }

    public void close() {
        isCloseCalled = true;
    }

    public boolean isCreateSocketAndListenCalled() {
        return isCreateSocketAndListenCalled;
    }

    public String getReceivedClientMessage() {
        return receivedClientMessage;
    }

    public boolean isCloseCalled() {
        return isCloseCalled;
    }
}
