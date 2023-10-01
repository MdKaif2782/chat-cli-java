package ai.kf;

import ai.kf.Azure.PubSub;
import com.azure.messaging.webpubsub.models.GetClientAccessTokenOptions;
import com.azure.messaging.webpubsub.models.WebPubSubClientAccessToken;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class AzurePubSub {
    public static void main(String[] args) throws URISyntaxException, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter your name: ");
        String name = scanner.nextLine();
        PubSub.startListener("chat",name);
        PubSub.sendMessage("chat", name + " has joined the chat!");
        while (true) {
            String message = scanner.nextLine();
            PubSub.sendMessage("chat", name + ": " + message);
        }
    }
}
