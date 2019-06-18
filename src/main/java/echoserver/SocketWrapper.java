package echoserver;

public interface SocketWrapper {

    void createSocketAndListen();
    void receiveMessage();
    void sendMessage(String message);
    void close();
}
