import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class LogInRequest {
    private static String responseToken;
    static String jsonBody = """
            {
              "userName": "admin",
              "password": "Gbtc-123$"
            }""";
    private static CloseableHttpResponse response;

    public static String send() {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost httpPost = new HttpPost("http://185.205.88.253:8081/transguard/bls/auth/token/login");
            StringEntity entity = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-Type", "application/json");

            response = httpClient.execute(httpPost);

            return getResponseToken(response);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static CloseableHttpResponse getResponse() {
        return response;
    }

    public static String getResponseToken(CloseableHttpResponse response) throws IOException {
        String token = "";
        try (InputStream inputStream = response.getEntity().getContent()) {
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                token = scanner.nextLine();
            }
        }
        return token.substring(17, token.length()-127);
    }
}
