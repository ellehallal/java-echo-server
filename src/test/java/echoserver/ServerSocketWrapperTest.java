package echoserver;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ServerSocketWrapperTest {

    MockServerSocketWrapper mockServerSocketWrapperSetup(String fakeMessage) {
        var message = new StringReader(fakeMessage);
        var input = new BufferedReader(message);
        var output = new PrintWriter(new StringWriter());
        return new MockServerSocketWrapper(input, output);
    }

    @Test
    void isCreateSocketAndListenCalledIsTrue() {
        var socketWrapper = mockServerSocketWrapperSetup("hello\n");

        socketWrapper.run();

        assertTrue(socketWrapper.isCreateSocketAndListenCalled());
    }

    @Test
    void receivedMessageEqualsHello() {
        var socketWrapper = mockServerSocketWrapperSetup("hello\n");

        socketWrapper.run();
        var receivedMessage = socketWrapper.getReceivedClientMessage();

        assertEquals("hello", receivedMessage);
    }

    @Test
    void sendMessage() {
        var output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        var socketWrapper = mockServerSocketWrapperSetup("hello\n");

        socketWrapper.run();

        assertEquals("Echo from server: hello\n", output.toString());
    }

    @Test
    void close() {
        var socketWrapper = mockServerSocketWrapperSetup("hello\n");

        socketWrapper.run();
        var isCloseCalled = socketWrapper.isCloseCalled();

        assertTrue(isCloseCalled);
    }
}