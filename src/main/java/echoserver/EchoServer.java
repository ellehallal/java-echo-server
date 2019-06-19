package echoserver;

public class EchoServer {
    public final SocketWrapper socket;

    public EchoServer(SocketWrapper socket) {
        this.socket = socket;
    }

    public void start() {
        socket.run();
    }
}
