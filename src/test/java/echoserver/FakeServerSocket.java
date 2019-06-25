package echoserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class FakeServerSocket extends ServerSocket {
    private List<FakeClientSocket> fakeClientSockets = new ArrayList<>();
    private final OutputStream output;

    FakeServerSocket(OutputStream output) throws IOException {
        this.output = output;
    }

    @Override
    public Socket accept() {
        return fakeClientSockets.remove(0);
    }

    void setupFakeClientSockets() {
        var input1 = new ByteArrayInputStream("Client 1's message".getBytes());
        var input2 = new ByteArrayInputStream("Client 2's message".getBytes());
        var input3 = new ByteArrayInputStream("Client 3's message".getBytes());
        var input4 = new ByteArrayInputStream("Client 4's message".getBytes());

        fakeClientSockets.add(new FakeClientSocket(input1, output));
        fakeClientSockets.add(new FakeClientSocket(input2, output));
        fakeClientSockets.add(new FakeClientSocket(input3, output));
        fakeClientSockets.add(new FakeClientSocket(input4, output));
    }
}
