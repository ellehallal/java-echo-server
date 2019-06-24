package echoserver;

class SocketOpenException extends RuntimeException {

    SocketOpenException(Exception cause) {
        super(cause);
    }
}
