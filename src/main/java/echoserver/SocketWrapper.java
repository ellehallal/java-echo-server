package echoserver;

public interface SocketWrapper {

    void createSocketAndListen(int portNumber);
    String receiveMessage();
    void sendMessage(String message);
    void close();
}
