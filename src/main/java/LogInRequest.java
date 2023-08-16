import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
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
        return token.substring(17, token.length() - 785);
    }

    //

    public static String getBearerTokenValue(CloseableHttpResponse response) throws IOException {
        String token = "";
        try (InputStream inputStream = response.getEntity().getContent()) {
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("Bearer ")) {
                    token = line.substring("Bearer ".length());
                    break; // Once we find the token, we can exit the loop
                }
            }
        }
        return token.substring(17, token.length()-127);
    }

    public static String getBearerTokenOnly(CloseableHttpResponse response) throws IOException {
        return getBearerTokenValue(response);
    }


    public static String parser() {
        String input = send();
        String accessToken = "";

        int startIndex = input.indexOf("acces_token:") + "acces_token:".length();
        int endIndex = input.indexOf("\",", startIndex);

        if (startIndex >= 0 && endIndex >= 0) {
            accessToken = input.substring(startIndex, endIndex);
            System.out.println("Access Token: " + accessToken);
        } else {
            System.out.println("Access token not found or invalid format.");
        }
        return accessToken;
    }


//    public String parser1() {
//
//        String test = send();
//
//        HashMap<String, String> hash_map = new HashMap<String, String>();
//
//        hash_map = send();
//
//        System.out.println("Initial Mappings are: " + hash_map);
//
//        System.out.println("The Value is: " + hash_map.get("Geeks"));
//
//        System.out.println("The Value is: " + hash_map.get("You"));
//
//        return
//    }

//    ObjectMapper objectMapper = new ObjectMapper();
//        try {
//        JsonNode jsonNode = objectMapper.readTree(send());
//        String accessToken = jsonNode.get("access_token").asText();
//        System.out.println("Access Token: " + accessToken);
//    } catch (Exception e) {
//        e.printStackTrace();
//    }


}