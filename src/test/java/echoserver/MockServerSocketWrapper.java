package echoserver;

import java.io.BufferedReader;
import java.io.IOException;

public class MockServerSocketWrapper implements SocketWrapper {

    private final BufferedReader input;
    private boolean isCreateSocketAndListenCalled = false;
    private String receivedClientMessage;
    private boolean isSendClientMessageCalled = false;
    private boolean isCloseCalled = false;

    public MockServerSocketWrapper(BufferedReader input) {
        this.input = input;
    }

    public void run() {
        createSocketAndListen();

        while(true) {
            receiveClientMessage2();
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
    }

    @Override
    public String receiveClientMessage2() {
        try {
            receivedClientMessage = input.readLine();
            return receivedClientMessage;
        } catch (IOException e) {
            System.out.println("Error receiving message: " + e.getMessage());
            return receivedClientMessage;
        }
    }

    public void sendClientMessage() {
        System.out.println("Echo from server: " + receivedClientMessage);
        isSendClientMessageCalled = true;
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

    public boolean isSendClientMessageCalled() {
        return isSendClientMessageCalled;
    }
}
