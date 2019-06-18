package echoserver;

public interface SocketWrapper {

    void createPortAndListen(int portNumber);
    String receiveMessage();
    void sendMessage(String message);
    void close();
}
