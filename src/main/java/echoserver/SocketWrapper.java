package echoserver;

public interface SocketWrapper {

    void createSocketAndListen();
    void receiveClientMessage();
    void sendClientMessage();
    void close();
}
