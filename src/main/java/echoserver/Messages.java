package echoserver;

class Messages {

    static String listeningForClientsMessage() {
        return "Listening for clients...";
    }

    static String clientConnectedMessage() {
        return "Client connected";
    }

    static String clientDisconnectedMessage() {
        return "Client disconnected";
    }

    static String clientInstructionsMessage() {
        return "Send messages to the server. Type 'exit' to disconnect from the server.";
    }

    static String echoFromServerMessage(String clientMessage) {
        return "Echo from server: " + clientMessage;
    }

    static String serverErrorMessage(String message) {
        return "Server error: " + message;
    }

    static String socketOpeningErrorMessage(String message) {
        return "Socket opening error: " + message;
    }

    static String socketClosingErrorMessage(String message) {
        return "Socket closing error: " + message;
    }
}
