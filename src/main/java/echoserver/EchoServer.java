package echoserver;

public class EchoServer {
    public static void main(String[] args) {
        var serverSocketWrapper = new ServerSocketWrapper(5000);
        serverSocketWrapper.start();
    }
}
