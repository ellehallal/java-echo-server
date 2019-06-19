package echoserver;

public interface SocketWrapper {

    void run();
    void createSocketAndListen();
    void receiveClientMessage();
    void sendClientMessage();
    void close();
}
