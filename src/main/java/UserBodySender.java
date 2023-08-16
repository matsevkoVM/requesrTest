import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;

public class UserBodySender {
    private static String begin = "{\"id\":";
    private static String middle = "";

    private static String end = ",\n" +
            "    \"fullName\": \"კარგი კლიენტი\",\n" +
            "    \"resident\": {\n" +
            "        \"iso2\": \"GE\",\n" +
            "        \"iso3\": null\n" +
            "    },\n" +
            "    \"passportId\": null,\n" +
            "    \"documentId\": ";


    private static String documentId = "";
    private static String finnish = ",\n" +
            "    \"address\": \"საქართველო\",\n" +
            "    \"legalAddress\": \"სდფგჰჯკ\",\n" +
            "    \"actualAddress\": \"სდფგჰჯკ\",\n" +
            "    \"passportIssuerAddress\": \"\",\n" +
            "    \"citizenship\": {\n" +
            "        \"iso2\": \"GE\",\n" +
            "        \"iso3\": null\n" +
            "    },\n" +
            "    \"identificationIssuerAddress\": \"\",\n" +
            "    \"birthDate\": \"1995-07-19\",\n" +
            "    \"juridical\": false,\n" +
            "    \"relatedPeople\": []\n" +
            "}";
    private static CloseableHttpResponse response;

    public static void send(String token) {
        JsonBody body = new JsonBody(buildJsonBody(begin, middle, end, documentId, finnish));
        String jsonBody = body.getBody();
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost httpPost = new HttpPost("http://185.205.88.253:8081/transguard/bls/check/client/72");
            StringEntity entity = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);


            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Authorization", "Bearer " + token);

            response = httpClient.execute(httpPost);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String buildJsonBody(String str0, String str1, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder();
        return sb.append(str0).append(str1).append(str2).append(str3).append(str4).toString();
    }

    public static void setMiddle(String middle) {
        UserBodySender.middle = middle;
    }

    public static void setDocumentId(String documentId) {
        UserBodySender.documentId = documentId;
    }

    public static CloseableHttpResponse getResponse() {
        return response;
    }

    protected static void sendTest() {
        //System.out.println(middle);
    }
}
