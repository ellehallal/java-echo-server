package echoserver;

public interface SocketWrapper {

    void run();
    void createSocketAndListen();
    void receiveClientMessage();
    String receiveClientMessage2();
    void sendClientMessage();
    void close();
}
