package echoserver;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class EchoServerTest {
    void addFakeClientSocketsToList(ArrayList<FakeClientSocket> fakeClientSockets, OutputStream output) {
        var input1 = new ByteArrayInputStream("Client 1's message".getBytes());
        var input2 = new ByteArrayInputStream("Client 2's message".getBytes());
        var input3 = new ByteArrayInputStream("Client 3's message".getBytes());
        fakeClientSockets.add(new FakeClientSocket(input1, output));
        fakeClientSockets.add(new FakeClientSocket(input2, output));
        fakeClientSockets.add(new FakeClientSocket(input3, output));
    }


    @Test
    void connectsToThreeClientsConcurrently() throws IOException {
        var expectedOutput = "Echo from server: Client 1's message\n" +
                "Echo from server: Client 2's message\n" +
                "Echo from server: Client 3's message";
        var output = new ByteArrayOutputStream();

        var fakeClientSockets = new ArrayList<FakeClientSocket>();
        addFakeClientSocketsToList(fakeClientSockets, output);
        var serverSocket = new FakeServerSocket(fakeClientSockets);
        var echoServer = new EchoServer(serverSocket);

        echoServer.start();


        assertThat(output.toString()).contains(expectedOutput);
    }
}
