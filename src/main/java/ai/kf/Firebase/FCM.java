package ai.kf.Firebase;

import com.google.firebase.messaging.FirebaseMessaging;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class FCM {
    private static final String BASE_URL = "https://fcm.googleapis.com";
    private static final String FCM_SEND_ENDPOINT = "/v1/projects/idkman-4009a/messages:send";
    public static void sendMessageToRoom(String room, String msg) throws IOException {
        // Create a JSON object
        JSONObject message = new JSONObject();
        JSONObject notification = new JSONObject();
        JSONObject data = new JSONObject();

        data.put("message", msg);
        notification.put("body", msg);
        message.put("topic", room);
        message.put("data", data);
        // Convert the JSON object to a string
        String jsonString = message.toJSONString();
        // Print the JSON string
        System.out.println(jsonString);

        URL url = new URL(BASE_URL + FCM_SEND_ENDPOINT);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("Authorization", "Bearer " + FCMToken.getAccessToken());
        httpURLConnection.setRequestProperty("Content-Type", "application/json; UTF-8");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.getOutputStream().write(jsonString.getBytes());
        System.out.println(httpURLConnection.getResponseCode());
        System.out.println(httpURLConnection.getResponseMessage());


    }
}
