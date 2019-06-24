package echoserver;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageSenderTest {

    @Test
    void echosMessagesToOutputUntilExitMessage() {
        var message = new StringReader("hello\nexit\n");
        var input = new BufferedReader(message);
        var stringWriter = new StringWriter();
        var output = new PrintWriter(stringWriter);

        var serverSocketWrapper = new MessageSender(input, output);

        serverSocketWrapper.run();

        assertEquals(Messages.echoFromServerMessage("hello\n"), stringWriter.toString());
    }

    @Test
    void echosMessagesToOutputUntilInputEnds() {
        var message = new StringReader("hello\n");
        var input = new BufferedReader(message);
        var stringWriter = new StringWriter();
        var output = new PrintWriter(stringWriter);

        var serverSocketWrapper = new MessageSender(input, output);

        serverSocketWrapper.run();

        assertEquals(Messages.echoFromServerMessage("hello\n"), stringWriter.toString());
    }
}