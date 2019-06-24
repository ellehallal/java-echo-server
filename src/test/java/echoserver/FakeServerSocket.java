package echoserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class FakeServerSocket extends ServerSocket {
    private final List<FakeClientSocket> fakeClientSockets;

    FakeServerSocket(ArrayList<FakeClientSocket> fakeClientSockets) throws IOException {
        this.fakeClientSockets = fakeClientSockets;
    }

    @Override
    public Socket accept() {
        return fakeClientSockets.remove(0);
       }
}
