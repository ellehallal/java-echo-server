package echoserver;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;

import static org.junit.jupiter.api.Assertions.*;

class EchoServerTest {
    @Test
    void connectsToThreeClientsConcurrently() throws IOException {
        var output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        var serverSocket = new ServerSocket(8080);
        var echoServer = new EchoServer(serverSocket);

        echoServer.listenForClients();
        echoServer.listenForClients();
        echoServer.listenForClients();

        var expectedOutput = "client 1 connected\nclient 2 connected\nclient 3 connected";

        assertEquals(expectedOutput, output.toString());
    }
}
