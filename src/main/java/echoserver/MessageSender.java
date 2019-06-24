package echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MessageSender {

    private final BufferedReader input;
    private final PrintWriter output;

    MessageSender(BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
    }

    void run() {
        while (true) {
            var message = receiveMessage();
            if (isEndOfMessages(message)) {
                break;
            }
            sendMessage(message);
        }
    }

    private boolean isEndOfMessages(String message) {
        return message == null || message.equals(SocketAction.exit.toString());
    }

    private String receiveMessage() {
        try {
            return input.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendMessage(String message) {
        output.println(Messages.echoFromServerMessage(message));
    }
}
