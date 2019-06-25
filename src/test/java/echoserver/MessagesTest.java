package echoserver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessagesTest {

    @Test
    void returnsClientConnectedMessage() {
        var message = Messages.clientConnectedMessage();

        assertEquals("Client connected", message);
    }

    @Test
    void returnsClientDisconnectedMessage() {
        var message = Messages.clientDisconnectedMessage();

        assertEquals("Client disconnected", message);
    }

    @Test
    void returnsClientInstructionsMessage() {
        var message = Messages.clientInstructionsMessage();
        var expectedMessage = "Send messages to the server. Type 'exit' to disconnect from the server.";

        assertEquals(expectedMessage, message);
    }

    @Test
    void returnsEchoFromServerMessage() {
        var message = Messages.echoFromServerMessage("hello");

        assertEquals("Echo from server: hello", message);
    }

    @Test
    void returnsServerErrorMessage() {
        var message = Messages.serverErrorMessage("an error");

        assertEquals("Server error: an error", message);
    }

    @Test
    void returnsSocketOpeningErrorMessage() {
        var message = Messages.socketOpeningErrorMessage("an error");

        assertEquals("Socket opening error: an error", message);
    }

    @Test
    void returnsSocketClosingErrorMessage() {
        var message = Messages.socketClosingErrorMessage("an error");

        assertEquals("Socket closing error: an error", message);
    }

    @Test
    void returnsListeningForClientsMessage() {
        var message = Messages.listeningForClientsMessage();

        assertEquals("Listening for clients...", message);
    }

    @Test
    void returnsServerConnectedMessage() {
        var message = Messages.serverConnectedMessage();

        assertEquals("Server connected", message);
    }
}