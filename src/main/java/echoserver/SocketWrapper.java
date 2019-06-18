package echoserver;

import java.io.IOException;

public interface SocketWrapper {

    void createSocketAndListen();
    String receiveMessage();
    void sendMessage(String message);
    void close();
}
