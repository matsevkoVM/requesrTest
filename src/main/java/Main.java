import org.apache.hc.core5.http.ProtocolException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws ProtocolException {

        ExecutorService executor = Executors.newFixedThreadPool(10);

            for (int i = 0; i < 10; i++) {
                executor.execute(new Requests());
            }
        executor.shutdown();
    }
}
