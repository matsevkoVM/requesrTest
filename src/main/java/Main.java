import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {



        ExecutorService executor = Executors.newFixedThreadPool(10);



        executor.execute(new Requests());
        executor.execute(new Requests());
        executor.execute(new Requests());
        executor.execute(new Requests());
        executor.execute(new Requests());
        executor.execute(new Requests());
        executor.execute(new Requests());
        executor.execute(new Requests());
        executor.execute(new Requests());
        executor.execute(new Requests());


        executor.shutdown();



//        Thread request = new Thread(new Requests());
//        Thread request1 = new Thread(new Requests());
//        Thread request2 = new Thread(new Requests());
//        Thread request3 = new Thread(new Requests());
//        Thread request4 = new Thread(new Requests());
//        Thread request5 = new Thread(new Requests());
//        Thread request6 = new Thread(new Requests());
//        Thread request7 = new Thread(new Requests());
//        Thread request8 = new Thread(new Requests());
//        Thread request9 = new Thread(new Requests());
//
//        request.start();
//        request1.start();
//        request2.start();
//        request3.start();
//        request4.start();
//        request5.start();
//        request6.start();
//        request7.start();
//        request8.start();
//        request9.start();




    }




}
