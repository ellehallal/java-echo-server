package echoserver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DisplayTest {
    Display display = new Display();

    @Test
    void returnsClientConnectedMessage() {
        var message = display.clientConnectedMessage();

        assertEquals("Client connected", message);
    }

    @Test
    void returnsClientDisconnectedMessage() {
        var message = display.clientDisconnectedMessage();

        assertEquals("Client disconnected", message);
    }

    @Test
    void returnsEchoFromServerMessage() {
        var message = display.echoFromServerMessage("hello");

        assertEquals("Echo from server: hello", message);
    }

    @Test
    void returnsServerErrorMessage() {
        var message = display.serverErrorMessage("an error");

        assertEquals("Server error: an error", message);
    }

    @Test
    void returnsSocketClosingErrorMessage() {
        var message = display.socketClosingErrorMessage("an error");

        assertEquals("Socket closing error: an error", message);
    }
}