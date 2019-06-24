package echoserver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessagesTest {
    Messages messages = new Messages();

    @Test
    void returnsClientConnectedMessage() {
        var message = messages.clientConnectedMessage();

        assertEquals("Client connected", message);
    }

    @Test
    void returnsClientDisconnectedMessage() {
        var message = messages.clientDisconnectedMessage();

        assertEquals("Client disconnected", message);
    }

    @Test
    void returnsClientInstructionsMessage() {
        var message = messages.clientInstructionsMessage();
        var expectedMessage = "Send messages to the server. Type 'exit' to disconnect from the server.";

        assertEquals(expectedMessage, message);
    }

    @Test
    void returnsEchoFromServerMessage() {
        var message = messages.echoFromServerMessage("hello");

        assertEquals("Echo from server: hello", message);
    }

    @Test
    void returnsServerErrorMessage() {
        var message = messages.serverErrorMessage("an error");

        assertEquals("Server error: an error", message);
    }

    @Test
    void returnsSocketOpeningErrorMessage() {
        var message = messages.socketOpeningErrorMessage("an error");

        assertEquals("Socket opening error: an error", message);
    }

    @Test
    void returnsSocketClosingErrorMessage() {
        var message = messages.socketClosingErrorMessage("an error");

        assertEquals("Socket closing error: an error", message);
    }
}