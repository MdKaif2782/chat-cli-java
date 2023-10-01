package ai.kf.Azure;

import com.azure.messaging.webpubsub.WebPubSubServiceClient;
import com.azure.messaging.webpubsub.WebPubSubServiceClientBuilder;
import com.azure.messaging.webpubsub.models.GetClientAccessTokenOptions;
import com.azure.messaging.webpubsub.models.WebPubSubClientAccessToken;
import com.azure.messaging.webpubsub.models.WebPubSubContentType;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class PubSub {
    public static WebPubSubServiceClient connection(String hub) {
        return new WebPubSubServiceClientBuilder()
                .connectionString(System.getenv("endpoint"))
                .hub(hub)
                .buildClient();
    }

    public static void sendMessage(String hub, String msg) {
        WebPubSubServiceClient client = connection(hub);
        client.sendToAll(msg, WebPubSubContentType.TEXT_PLAIN);
    }

    public static void startListener(String hub,String name) throws URISyntaxException, IOException {
        WebPubSubClientAccessToken token = PubSub.connection(hub).getClientAccessToken(new GetClientAccessTokenOptions());
        WebSocketClient client = new WebSocketClient(new URI(token.getUrl())) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {

            }

            @Override
            public void onMessage(String message) {
                if (!message.startsWith(name)){
                System.out.print("\n"+message+"\nYou: ");
                }
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {

            }

            @Override
            public void onError(Exception ex) {

            }
        };
        client.connect();
        System.in.read();
    }
}
