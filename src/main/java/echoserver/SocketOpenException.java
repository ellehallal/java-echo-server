package echoserver;

class SocketOpenException extends RuntimeException {

    public SocketOpenException(Exception cause) {
        super(cause);
    }
}
