package echoserver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServerSocketWrapperTest {

    @Test
    void isCreateSocketAndListenCalledIsTrue() {
        var socketWrapper = new MockServerSocketWrapper();
        socketWrapper.run();
        assertTrue(socketWrapper.isCreateSocketAndListenCalled());
    }

    @Test
    void receiveMessage() {
    }

    @Test
    void sendMessage() {
    }

    @Test
    void close() {
    }
}