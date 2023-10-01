package ai.kf.Firebase;

import com.google.auth.oauth2.GoogleCredentials;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class FCMToken {

    private static final String SCOPES = "https://www.googleapis.com/auth/firebase.messaging";
    public static String getAccessToken() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new FileInputStream("idkman-4009a-firebase-adminsdk-hvo1h-9a04d17f2a.json"))
                .createScoped(Arrays.asList(SCOPES));
        googleCredentials.refresh();
        return googleCredentials.getAccessToken().getTokenValue();
    }
}
