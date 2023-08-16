import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;

public class LogInRequest {
    static String jsonBody = """
            {
              "userName": "string",
              "password": "string"
            }""";
    private static CloseableHttpResponse response;
    public static void send() {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost httpPost = new HttpPost("http://10.19.25.120:8081/transguard/bls/swagger-ui/index.html?configUrl=/transguard/bls/v3/api-docs/swagger-config#/auth-controller/codeToToken/auth/token/login");
            StringEntity entity = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-Type", "application/json");

            response = httpClient.execute(httpPost);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
