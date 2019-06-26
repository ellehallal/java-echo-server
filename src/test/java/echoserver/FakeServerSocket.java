package echoserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FakeServerSocket extends ServerSocket {
    private final List<FakeClientSocket> acceptedClientSockets = new ArrayList<>();
    private final List<String> fakeClientMessages = new ArrayList<>
            (Arrays.asList("Client 1's message", "Client 2's message",
                    "Client 3's message", "Client 4's message"));

    FakeServerSocket() throws IOException {
    }

    @Override
    public Socket accept() {
        var socket = setupFakeClientSocket();
        acceptedClientSockets.add(socket);
        return socket;
    }

    private FakeClientSocket setupFakeClientSocket() {
        var clientMessage = fakeClientMessages.remove(0);
        var input = new ByteArrayInputStream(clientMessage.getBytes());
        var output =  new ByteArrayOutputStream();
        return new FakeClientSocket(input, output);
    }

    String getSocketOutputString(int index) {
        return getAcceptedClientSockets().get(index).getOutputStream().toString();
    }

    private List<FakeClientSocket> getAcceptedClientSockets() {
        return acceptedClientSockets;
    }
}
