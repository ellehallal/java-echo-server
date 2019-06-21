package echoserver;

public class Messages {

    public static String clientConnectedMessage() {
        return "Client connected";
    }

    public static String clientDisconnectedMessage() {
        return "Client disconnected";

    }

    public static String echoFromServerMessage(String clientMessage) {
        return "Echo from server: " + clientMessage;
    }

    public static String serverErrorMessage(String message) {
        return "Server error: " + message;
    }

    public static String socketClosingErrorMessage(String message) {
        return "Socket closing error: " + message;
    }
}
