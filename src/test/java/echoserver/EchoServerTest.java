package echoserver;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EchoServerTest {
    @Test
    void returnsInputOfFourDifferentClients() throws IOException, InterruptedException {
        var expectedConnectedClients = 4;
        var expectedOutput1 = "Echo from server: Client 1's message\n";
        var expectedOutput2 = "Echo from server: Client 2's message\n";
        var expectedOutput3 = "Echo from server: Client 3's message\n";
        var expectedOutput4 = "Echo from server: Client 4's message\n";
        var output = new ByteArrayOutputStream();
        var fakeServerSocket = new FakeServerSocket(output);
        fakeServerSocket.setupFakeClientSockets();
        var executor = Executors.newFixedThreadPool(4);
        var echoServer = new EchoServer(fakeServerSocket, executor);

        echoServer.listenForClients();
        echoServer.listenForClients();
        echoServer.listenForClients();
        echoServer.listenForClients();
        Thread.sleep(100);

        var outputString = output.toString();

        assertThat(outputString).contains(expectedOutput1);
        assertThat(outputString).contains(expectedOutput2);
        assertThat(outputString).contains(expectedOutput3);
        assertThat(outputString).contains(expectedOutput4);
        assertEquals(expectedConnectedClients, echoServer.getClientSockets().size());
    }
}
