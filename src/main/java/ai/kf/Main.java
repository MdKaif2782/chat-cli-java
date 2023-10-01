package ai.kf;

import ai.kf.Firebase.FCM;
import ai.kf.Firebase.FCMToken;
import ai.kf.Firebase.Initiator;
import com.google.api.core.ApiFuture;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.TopicManagementResponse;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
import com.google.firebase.remoteconfig.Parameter;
import com.google.protobuf.Api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws IOException, FirebaseMessagingException, InterruptedException, ExecutionException, FirebaseRemoteConfigException {
        Initiator.initiate();
        String token = FCMToken.getAccessToken();
        System.out.println(token);
//        FCM.sendMessageToRoom("room1", "Hello World");
        List<String> tokens = new ArrayList<>();
        tokens.add(FCMToken.getAccessToken());
        FirebaseMessaging fcm = FirebaseMessaging.getInstance();

        Message message = Message.builder()
                .putData("message", "Hello World")
                .setTopic("news")
                .build();
        ApiFuture<String> apiFuture = fcm.sendAsync(message);
        System.out.println("Message sent successfully");
        System.out.println(apiFuture.get());

        // Receive message from FCM
        ApiFuture<TopicManagementResponse> apiFuture1 = fcm.subscribeToTopicAsync(tokens, "news");
        System.out.println("Subscribed to topic successfully");
        System.out.println(apiFuture1.get().getSuccessCount());



    }
}