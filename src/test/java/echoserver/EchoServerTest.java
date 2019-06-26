package echoserver;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

class EchoServerTest {

    @Test
    void returnsInputOfFourDifferentClients() throws IOException, InterruptedException {

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

        assertThat(fakeServerSocket.getSocketOutputString(0)).contains(expectedOutput1);
        assertThat(fakeServerSocket.getSocketOutputString(1)).contains(expectedOutput2);
        assertThat(fakeServerSocket.getSocketOutputString(2)).contains(expectedOutput3);
        assertThat(fakeServerSocket.getSocketOutputString(3)).contains(expectedOutput4);
    }
}
