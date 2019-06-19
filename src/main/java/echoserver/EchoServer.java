package echoserver;

public class EchoServer {
    public static void main(String[] args) {
        var serverSocketWrapper = new ServerSocketWrapper(8080);
        serverSocketWrapper.start();
    }
}
