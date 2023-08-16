import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

public class Requests implements Runnable {
    private String token = LogInRequest.send();
    private final long currentTime = System.currentTimeMillis();
    @Override
    public void run() {
        if (tryToRunTransactionBodySender() == 401) {
            token = LogInRequest.send();
            tryToRunTransactionBodySender();
        } else {
            tryToRunTransactionBodySender();
        }

        if (tryToRunUserBodySender() == 401){
            token = LogInRequest.send();
            tryToRunUserBodySender();
        } else {
            tryToRunUserBodySender();
        }


    }

    private int tryToRunTransactionBodySender(){
        for (int i = 0; i < 100; i++) {
            UUID uid = UUID.randomUUID();
            TransactionBodySender.setMiddle("\"" + uid + "\"");
            TransactionBodySender.send(token);
            System.out.println(Thread.currentThread().getName());
            System.out.println(TransactionBodySender.getResponse());
            System.out.println("Transaction Body Sender response" + TransactionBodySender.getResponse());
        }
        return TransactionBodySender.getResponse().getCode();
    }
    private int tryToRunUserBodySender(){
        long transactionExecuteTime = System.currentTimeMillis();

        System.out.println("Time executed for transactions: " + (transactionExecuteTime - currentTime));

        for (int i = 0; i < 100; i++) {
            UUID uid = UUID.randomUUID();
            UserBodySender.setMiddle("\"" + uid + "\"");
            UserBodySender.setDocumentId("\"" + generateId() + "\"");
            UserBodySender.send(token);
            System.out.println("Client: " + Thread.currentThread().getName() + " " + uid);
            System.out.println(TransactionBodySender.getResponse());
        }
        System.out.println("Time executed for clients: " + (System.currentTimeMillis() - transactionExecuteTime));

        System.out.println("Total executed time: " + (System.currentTimeMillis() - currentTime));
        return UserBodySender.getResponse().getCode();
    }

    private String generateId() {
        return RandomStringUtils.randomNumeric(10);
    }
}
