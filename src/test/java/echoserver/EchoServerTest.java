package echoserver;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class EchoServerTest {

    MockServerSocketWrapper mockServerSocketWrapperSetup(String fakeMessage) {
        var message = new StringReader(fakeMessage);
        var input = new BufferedReader(message);
        return new MockServerSocketWrapper(input);
    }

    @Test
    void isCreateSocketAndListenCalledIsTrue() {
        var socketWrapper = mockServerSocketWrapperSetup("hello\n");
        var echoServer = new EchoServer(socketWrapper);

        echoServer.start();

        assertTrue(socketWrapper.isCreateSocketAndListenCalled());
    }

    @Test
    void receivedMessageEqualsHello() {
        var socketWrapper = mockServerSocketWrapperSetup("hello\n");
        var echoServer = new EchoServer(socketWrapper);

        echoServer.start();
        var receivedMessage = socketWrapper.getReceivedClientMessage();

        assertEquals("hello", receivedMessage);
    }

    @Test
    void sentMessageIncludesHello() {
        var output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        var socketWrapper = mockServerSocketWrapperSetup("hello\n");
        var echoServer = new EchoServer(socketWrapper);

        echoServer.start();
        var isSendClientMessageCalled = socketWrapper.isSendClientMessageCalled();

        assertEquals("Echo from server: hello\n", output.toString());
        assertTrue(isSendClientMessageCalled);
    }

    @Test
    void closeIsCalledWhenClientMessageEqualsExit() {
        var socketWrapper = mockServerSocketWrapperSetup("exit\n");
        var echoServer = new EchoServer(socketWrapper);

        echoServer.start();
        var isCloseCalled = socketWrapper.isCloseCalled();
        var isSendClientMessageCalled = socketWrapper.isSendClientMessageCalled();

        assertTrue(isCloseCalled);
        assertFalse(isSendClientMessageCalled);
    }
}