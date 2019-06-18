package echoserver;

public class MockServerSocketWrapper extends Thread implements SocketWrapper {

    private boolean isCreateSocketAndListenCalled = false;

    @Override
    public void run() {
        createSocketAndListen();
    }

    public void createSocketAndListen() {
        this.isCreateSocketAndListenCalled = true;
    }

    public String receiveMessage() {
        return null;
    }

    public void sendMessage(String message) {

    }

    public void close() {

    }

    public boolean isCreateSocketAndListenCalled() {
        System.out.println(isCreateSocketAndListenCalled);
        return isCreateSocketAndListenCalled;
    }
}
