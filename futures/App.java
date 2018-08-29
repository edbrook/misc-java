import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {

    private static final int MAX_THREADS = 5;
    private static final int MAX_SLEEP = 10000;

    private static ExecutorService executor
        = Executors.newFixedThreadPool(MAX_THREADS);
    
    private static final Random RANDOM = new Random();

    public static void main(String[] args) throws Exception {
        CompletableFuture<Void> promisedFuture = getPromise(2)
            .thenAccept(System.out::println);

        Future<Integer> future = getTheAnswer(21);
        while (!future.isDone()) {
            System.out.println("Waiting for answer to 21");
            Thread.sleep(1000);
        }
        System.out.println("The answer to 21 is: " + future.get());
        
        promisedFuture.get(); // Wait for CompletableFuture to finish
        System.out.println("\nPress [enter] to exit.");
        System.in.read();
        System.out.println("exiting...");
        executor.shutdown();
    }

    private static Future<Integer> getTheAnswer(int n) {
        return executor.submit(() -> {
            doSleep("The Answer will be available in");
            return 21 + n;
        });
    }

    private static CompletableFuture<String> getPromise(int n) {
        return CompletableFuture.supplyAsync(() -> {
            doSleep("Promised Answer available in");
            return "Promised Answer: " + (85 / n);
        }, executor);
    }

    private static final void doSleep(String message) {
        try {
            int sleep = RANDOM.nextInt(MAX_SLEEP);
            System.out.println(message + " " + sleep + "ms");
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            //
        }
    }
}