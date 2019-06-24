package echoserver;

public class Messages {

    public static String clientConnectedMessage() {
        return "Client connected";
    }

    public static String clientDisconnectedMessage() {
        return "Client disconnected";
    }

    public static String clientInstructionsMessage() {
        return "Send messages to the server. Type 'exit' to disconnect from the server.";
    }

    public static String echoFromServerMessage(String clientMessage) {
        return "Echo from server: " + clientMessage;
    }

    public static String serverErrorMessage(String message) {
        return "Server error: " + message;
    }

    public static String socketOpeningErrorMessage(String message) {
        return "Socket opening error: " + message;
    }

    public static String socketClosingErrorMessage(String message) {
        return "Socket closing error: " + message;
    }
}
