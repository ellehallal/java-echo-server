package echoserver;

public class RunServer {
    public static void main(String[] args) {
        var display = new Display();
        var socket = new ServerSocketWrapper(8080, display);
        new EchoServer(socket).start();
    }
}
