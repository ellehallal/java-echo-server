package echoserver;

public class RunServer {
    public static void main(String[] args) {
        var socket = new ServerSocketWrapper(5000);
        new EchoServer(socket).start();
    }
}
