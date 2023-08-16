import org.apache.hc.core5.http.ProtocolException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws ProtocolException {

        ExecutorService executor = Executors.newFixedThreadPool(10);

        long startTime = System.currentTimeMillis();
        long endTime = startTime + 10 * 60 * 60 * 1000;

        while (System.currentTimeMillis() < endTime) {
            executor.execute(new Requests());
        }
        executor.shutdown();

        System.out.println("Loop finished after 10 hours.");
    }

}
