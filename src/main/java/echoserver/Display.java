package echoserver;

public class Display {

    public String clientConnectedMessage() {
        return "Client connected";
    }

    public String clientDisconnectedMessage() {
        return "Client disconnected";

    }

    public String echoFromServerMessage(String clientMessage) {
        return "Echo from server: " + clientMessage;
    }

    public String serverErrorMessage(String message) {
        return "Server error: " + message;
    }

    public String socketClosingErrorMessage(String message) {
        return "Socket closing error: " + message;
    }
}
