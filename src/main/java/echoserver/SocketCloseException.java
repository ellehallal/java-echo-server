package echoserver;

class SocketCloseException extends RuntimeException {

    SocketCloseException(Exception cause) {
        super(cause);
    }
}
