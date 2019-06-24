package echoserver;

class SocketCloseException extends RuntimeException {

    public SocketCloseException(Exception cause) {
        super(cause);
    }
}
