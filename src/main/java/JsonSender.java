import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;

public class JsonSender {
    private static String begin = "{\n" +
            "    \"data\": {\n" +
            "        \"debit\": {\n" +
            "            \"clientNo\": \"7842877\",\n" +
            "            \"firstName\": \"ცქრიალა\",\n" +
            "            \"lastName\": \"ჩუტკერაშვილი\",\n" +
            "            \"relatedPeople\": [],\n" +
            "            \"balance\": null,\n" +
            "            \"altNames\": null,\n" +
            "            \"juridical\": false,\n" +
            "            \"citizenship\": {\n" +
            "                \"iso2\": \"GE\",\n" +
            "                \"iso3\": null\n" +
            "            },\n" +
            "            \"mail\": \"INFO@CREDO.GE\",\n" +
            "            \"phone\": \"599099248\",\n" +
            "            \"resident\": {\n" +
            "                \"iso2\": \"GE\",\n" +
            "                \"iso3\": null\n" +
            "            },\n" +
            "            \"georgianIdNumber\": \"00000000721\",\n" +
            "            \"identificationIssuerAddress\": null,\n" +
            "            \"passportId\": null,\n" +
            "            \"gender\": \"მამრობითი\",\n" +
            "            \"birthDate\": \"1990-09-15\",\n" +
            "            \"birthCountry\": \"საქართველო\",\n" +
            "            \"birthPlace\": null,\n" +
            "            \"documentType\": null,\n" +
            "            \"issuerName\": null,\n" +
            "            \"serie\": null,\n" +
            "            \"number\": null,\n" +
            "            \"after\": null,\n" +
            "            \"before\": null,\n" +
            "            \"documentIdentifier\": null,\n" +
            "            \"issuingCountry\": null,\n" +
            "            \"jobTitle\": null,\n" +
            "            \"fullName\": \"ცქრიალა ჩუტკერაშვილი\",\n" +
            "            \"legalForm\": null,\n" +
            "            \"regNumber\": null,\n" +
            "            \"regDate\": null,\n" +
            "            \"regOrganization\": null,\n" +
            "            \"regCountry\": null,\n" +
            "            \"taxPayerId\": null,\n" +
            "            \"taxPayerRegCountry\": null,\n" +
            "            \"legalAddress\": \"აბაშა\",\n" +
            "            \"actualAddress\": \"აბაშა\",\n" +
            "            \"bankRegDate\": \"2023-07-26\",\n" +
            "            \"accountNumber\": null,\n" +
            "            \"accountType\": null,\n" +
            "            \"bankName\": null,\n" +
            "            \"bic\": null,\n" +
            "            \"bankCountry\": null,\n" +
            "            \"bankPlace\": null,\n" +
            "            \"accountOpeningDate\": null,\n" +
            "            \"accountClosingDate\": null,\n" +
            "            \"activityArea\": null,\n" +
            "            \"riskLevel\": null,\n" +
            "            \"pep\": false,\n" +
            "            \"swift50\": null\n" +
            "        },\n" +
            "        \"credit\": {\n" +
            "            \"balance\": null,\n" +
            "            \"altNames\": null,\n" +
            "            \"clientNo\": 0,\n" +
            "            \"firstName\": null,\n" +
            "            \"lastName\": null,\n" +
            "            \"juridical\": true,\n" +
            "            \"citizenship\": null,\n" +
            "            \"mail\": null,\n" +
            "            \"phone\": null,\n" +
            "            \"resident\": null,\n" +
            "            \"georgianIdNumber\": null,\n" +
            "            \"identificationIssuerAddress\": null,\n" +
            "            \"passportId\": null,\n" +
            "            \"gender\": null,\n" +
            "            \"birthDate\": null,\n" +
            "            \"birthCountry\": null,\n" +
            "            \"birthPlace\": null,\n" +
            "            \"documentType\": null,\n" +
            "            \"issuerName\": null,\n" +
            "            \"serie\": null,\n" +
            "            \"number\": null,\n" +
            "            \"after\": null,\n" +
            "            \"before\": null,\n" +
            "            \"documentIdentifier\": null,\n" +
            "            \"issuingCountry\": null,\n" +
            "            \"jobTitle\": null,\n" +
            "            \"fullName\": \"კარგი კლიენტი\",\n" +
            "            \"legalForm\": null,\n" +
            "            \"regNumber\": null,\n" +
            "            \"regDate\": null,\n" +
            "            \"regOrganization\": null,\n" +
            "            \"regCountry\": null,\n" +
            "            \"taxPayerId\": null,\n" +
            "            \"taxPayerRegCountry\": null,\n" +
            "            \"legalAddress\": null,\n" +
            "            \"actualAddress\": null,\n" +
            "            \"bankRegDate\": null,\n" +
            "            \"accountNumber\": \"73CD0360000030429311USD\",\n" +
            "            \"accountType\": null,\n" +
            "            \"bankName\": \"BANKA KOMBETARE TREGTARE SH.A.\",\n" +
            "            \"bic\": \"NCBAALTX\",\n" +
            "            \"bankCountry\": null,\n" +
            "            \"bankPlace\": null,\n" +
            "            \"accountOpeningDate\": null,\n" +
            "            \"accountClosingDate\": null,\n" +
            "            \"activityArea\": null,\n" +
            "            \"riskLevel\": null,\n" +
            "            \"pep\": false,\n" +
            "            \"swift50\": null,\n" +
            "            \"relatedPeople\": null\n" +
            "        },\n" +
            "        \"recId\": ";
    private static String middle = "";
    private static String end = ",\n" +
            "        \"ApplicationName\": \"CashDesk\",\n" +
            "        \"amount\": 90.0,\n" +
            "        \"iso\": \"USD\",\n" +
            "        \"purpose\": \"პირადი გადარიცხვა\",\n" +
            "        \"location\": \"ლილო\",\n" +
            "        \"dealId\": null,\n" +
            "        \"docDate\": \"2023-07-27\",\n" +
            "        \"transactionType\": \"CurrencyTransfer\",\n" +
            "        \"amountEqu\": null,\n" +
            "        \"cashPayment\": null,\n" +
            "        \"extraPurpose\": \"\",\n" +
            "        \"distance\": false,\n" +
            "        \"intBank\": {\n" +
            "            \"NCBAALTX\": \"BANKA KOMBETARE TREGTARE SH.A.\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    private static CloseableHttpResponse response;

    public static void send(String token) {
        JsonBody body = new JsonBody(buildJsonBody(begin, middle, end));
        String jsonBody = body.getBody();
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost httpPost = new HttpPost("http://185.205.88.253:8081/transguard/bls/check/transaction/72");
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

    private static String buildJsonBody(String str0, String str1, String str2) {
        StringBuilder sb = new StringBuilder();
        return sb.append(str0).append(str1).append(str2).toString();
    }

    public static void setMiddle(String middle) {
        JsonSender.middle = middle;
    }

    public static CloseableHttpResponse getResponse() {
        return response;
    }

    protected static void sendTest() {
        //System.out.println(middle);
    }
}
