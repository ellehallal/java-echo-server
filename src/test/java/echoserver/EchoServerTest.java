package echoserver;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class EchoServerTest {

    @Test
    void returnsInputOfFourDifferentClients() throws IOException, InterruptedException {

        var expectedOutput1 = "Echo from server: Client 1's message\n";
        var expectedOutput2 = "Echo from server: Client 2's message\n";
        var expectedOutput3 = "Echo from server: Client 3's message\n";
        var expectedOutput4 = "Echo from server: Client 4's message\n";
        var fakeServerSocket = new FakeServerSocket();
        var executor = new CurrentThreadExecutor();
        var echoServer = new EchoServer(fakeServerSocket, executor);

        echoServer.listenForClients();
        echoServer.listenForClients();
        echoServer.listenForClients();
        echoServer.listenForClients();

        assertThat(fakeServerSocket.getSocketOutputString(0)).contains(expectedOutput1);
        assertThat(fakeServerSocket.getSocketOutputString(1)).contains(expectedOutput2);
        assertThat(fakeServerSocket.getSocketOutputString(2)).contains(expectedOutput3);
        assertThat(fakeServerSocket.getSocketOutputString(3)).contains(expectedOutput4);
    }
}
