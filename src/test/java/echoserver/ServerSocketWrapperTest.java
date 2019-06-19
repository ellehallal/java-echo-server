package echoserver;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        var output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        var socketWrapper = mockServerSocketWrapperSetup();

        socketWrapper.run();

        assertEquals("Echo from server: hello\n", output.toString());
    }

    @Test
    void close() {
    }
}