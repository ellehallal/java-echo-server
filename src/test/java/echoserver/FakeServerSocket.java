package echoserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class FakeServerSocket extends ServerSocket {
    private List<FakeClientSocket> fakeClientSockets = new ArrayList<>();
    private List<FakeClientSocket> acceptedClientSockets = new ArrayList<>();

    public FakeServerSocket() throws IOException {
    }

    @Override
    public Socket accept() {
        var socket = fakeClientSockets.remove(0);
        acceptedClientSockets.add(socket);
        return socket;
    }

    void setupFakeClientSockets() {
        var input1 = new ByteArrayInputStream("Client 1's message".getBytes());
        var input2 = new ByteArrayInputStream("Client 2's message".getBytes());
        var input3 = new ByteArrayInputStream("Client 3's message".getBytes());
        var input4 = new ByteArrayInputStream("Client 4's message".getBytes());

        fakeClientSockets.add(new FakeClientSocket(input1, new ByteArrayOutputStream()));
        fakeClientSockets.add(new FakeClientSocket(input2, new ByteArrayOutputStream()));
        fakeClientSockets.add(new FakeClientSocket(input3, new ByteArrayOutputStream()));
        fakeClientSockets.add(new FakeClientSocket(input4, new ByteArrayOutputStream()));
    }

    public List<FakeClientSocket> getAcceptedClientSockets() {
        return acceptedClientSockets;
    }
}
