package echoserver;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class FakeClientSocket extends Socket {
    private final InputStream inputStream;
    private final OutputStream outputStream;

    FakeClientSocket(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }
}
