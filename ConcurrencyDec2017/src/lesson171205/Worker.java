package lesson171205;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

public class Worker implements Executor {
    private BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();
    private static final Runnable POISON_PILL = () -> {
    };

    public Worker() {
        new Thread(this::processTasks).start();
    }

    public void execute(Runnable task) {
        tasks.offer(task);
    }

    public void shutDown() {
        tasks.offer(POISON_PILL);
    }

    private void processTasks() {
        while (true) {
            Runnable task = tasks.poll();
            if (task == POISON_PILL) {
                break;
            }
            task.run();
        }
    }
}
