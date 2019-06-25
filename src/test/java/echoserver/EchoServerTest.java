package echoserver;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.ServerSocket;
import java.util.ArrayList;
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
        var fakeServerSocket = new FakeServerSocket();
        fakeServerSocket.setupFakeClientSockets();
        var executor = Executors.newFixedThreadPool(4);
        var echoServer = new EchoServer(fakeServerSocket, executor);

        echoServer.listenForClients();
        echoServer.listenForClients();
        echoServer.listenForClients();
        echoServer.listenForClients();
        Thread.sleep(100);

        assertThat(getFakeClientSocketOutputString(0, fakeServerSocket)).contains(expectedOutput1);
        assertThat(getFakeClientSocketOutputString(1, fakeServerSocket)).contains(expectedOutput2);
        assertThat(getFakeClientSocketOutputString(2, fakeServerSocket)).contains(expectedOutput3);
        assertThat(getFakeClientSocketOutputString(3, fakeServerSocket)).contains(expectedOutput4);
        assertEquals(expectedConnectedClients, echoServer.getClientSockets().size());
    }

    String getFakeClientSocketOutputString(int index, FakeServerSocket fakeServerSocket) {
        return fakeServerSocket.getAcceptedClientSockets().get(index).getOutputStream().toString();
    }
}
