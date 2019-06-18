package echoserver;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

class ServerSocketWrapperTest {

    MockServerSocketWrapper mockServerSocketWrapperSetup() {
        var message = new StringReader("hello\n");
        var input = new BufferedReader(message);
        var output = new PrintWriter(new StringWriter());
        return new MockServerSocketWrapper(input, output);
    }

    @Test
    void isCreateSocketAndListenCalledIsTrue() {
        var socketWrapper = mockServerSocketWrapperSetup();

        socketWrapper.run();

        assertTrue(socketWrapper.isCreateSocketAndListenCalled());
    }

    @Test
    void receivedMessageEqualsHello() {
        var socketWrapper = mockServerSocketWrapperSetup();

        socketWrapper.run();
        var receivedMessage = socketWrapper.getReceivedClientMessage();

        assertEquals("hello", receivedMessage);
    }

    @Test
    void sendMessage() {
    }

    @Test
    void close() {
    }
}