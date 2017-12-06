package lesson171206;

import lesson171201.Utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UseWorker {

    public static void main(String[] args) {

        System.out.println("main started");

        ExecutorService worker = Executors.newSingleThreadExecutor();

        worker.execute(() -> {
            Utils.pause(1000);
            System.out.println("one");
        });

        worker.execute(() -> {
            Utils.pause(1000);
            System.out.println("two");
        });

        worker.execute(() -> {
            Utils.pause(1000);
            System.out.println("three");
        });

        System.out.println("main finished");
        worker.shutdown();

    }

}
