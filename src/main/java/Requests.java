import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

public class Requests implements Runnable {
    @Override
    public void run() {
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICIwWFEwbmRxWE16eG9tS2FrM2xtQWo0RERhV254VlRaY2s4dEhMWVZUZlE4In0.eyJleHAiOjE2OTA5NzA2MzAsImlhdCI6MTY5MDk2ODgzMCwianRpIjoiMTI0ZGM0MjctMGRiYS00ODNlLTgxMmUtMjRjMjQ0YTVjMGMwIiwiaXNzIjoiaHR0cDovL2F1dGg6ODA4MC9hdXRoL3JlYWxtcy90cmFuc2d1YXJkIiwiYXVkIjpbInJlYWxtLW1hbmFnZW1lbnQiLCJ0Zy1ib2ciXSwic3ViIjoiNjFlNDRkNzEtZDAxNy00ZTg2LWExM2ItZDA1YjU2YzAyOGZhIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoidGctYmxzIiwic2Vzc2lvbl9zdGF0ZSI6IjBjNGE2ZWIwLTYwNjEtNDA1MC1hN2U3LTZiMDUzNzUxMGM5OSIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJhZG1pbiIsInVtYV9hdXRob3JpemF0aW9uIiwidGctY3VzdG9tZXIiXX0sInJlc291cmNlX2FjY2VzcyI6eyJyZWFsbS1tYW5hZ2VtZW50Ijp7InJvbGVzIjpbInZpZXctaWRlbnRpdHktcHJvdmlkZXJzIiwidmlldy1yZWFsbSIsIm1hbmFnZS1pZGVudGl0eS1wcm92aWRlcnMiLCJpbXBlcnNvbmF0aW9uIiwicmVhbG0tYWRtaW4iLCJjcmVhdGUtY2xpZW50IiwibWFuYWdlLXVzZXJzIiwicXVlcnktcmVhbG1zIiwidmlldy1hdXRob3JpemF0aW9uIiwicXVlcnktY2xpZW50cyIsInF1ZXJ5LXVzZXJzIiwibWFuYWdlLWV2ZW50cyIsIm1hbmFnZS1yZWFsbSIsInZpZXctZXZlbnRzIiwidmlldy11c2VycyIsInZpZXctY2xpZW50cyIsIm1hbmFnZS1hdXRob3JpemF0aW9uIiwibWFuYWdlLWNsaWVudHMiLCJxdWVyeS1ncm91cHMiXX0sInRnLWJvZyI6eyJyb2xlcyI6WyJ1bWFfcHJvdGVjdGlvbiJdfX0sInNjb3BlIjoiZW1haWwgcHJvZmlsZSIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwiUm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJhZG1pbiIsInVtYV9hdXRob3JpemF0aW9uIiwidGctY3VzdG9tZXIiXSwicHJlZmVycmVkX3VzZXJuYW1lIjoiYWRtaW4ifQ.P29aP_AIsiXCUge8Pk5dm3h8IdmlFw4zxAVssMYiEl8ZwwwYoH11FyiqOboYU6Ujkji2pTwhe_ApTXGfKfA-xemYVrJ3gvmJ_mbOqpfR9Qg00WbTzS2fn7-qh-OjevdUhZkjZpMlEKtsDulA3aBah7GynOr68DZ_zgLi0YJcft93jwnzn3aeCXeBerh8ZQNXyHcqrNbGbHP-sT8nyAHz45WwUYlWce6zGrBJjgUzWKhqmEIa2BfZcQlGM6NrO-HVzXf9fr1wubNhMAxwyMnfjVaWQwXxGtOzog3EumbGJhY1IMTrWrDgBowZS5kGRqrnsrGrcctYrcnhpX0I2pApcA";
        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            UUID uid = UUID.randomUUID();
            JsonSender.setMiddle("\"" + uid + "\"");
            JsonSender.send(token);
            System.out.println(Thread.currentThread().getName());
            System.out.println(JsonSender.getResponse());

        }

        long transactionExecuteTime = System.currentTimeMillis();

        System.out.println(String.format("time executed for transactions: " + (transactionExecuteTime - currentTime)));

        for (int i = 0; i < 100; i++) {
            UUID uid = UUID.randomUUID();
            JsonSender1.setMiddle("\"" + uid + "\"");
            JsonSender1.setDocumentId("\"" + generateId() + "\"");
            JsonSender1.send(token);
            System.out.println("Client: " + Thread.currentThread().getName() + " " + uid);
            System.out.println(JsonSender.getResponse());
            //?
        }
        System.out.println(String.format("time executed for clients: " + (System.currentTimeMillis() - transactionExecuteTime)));

        System.out.println(String.format("total executed time: " + (System.currentTimeMillis() - currentTime)));



    }
    private String  generateId(){
        return RandomStringUtils.randomNumeric(10);
    }
}

// 33 number in one thread
// 33 number in second thread
// 34 number in third thread