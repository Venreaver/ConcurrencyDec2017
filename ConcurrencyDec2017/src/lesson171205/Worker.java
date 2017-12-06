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
        try {
            tasks.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shutDown() {
        try {
            tasks.put(POISON_PILL);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void processTasks() {
        while (true) {
            try {
                Runnable task = tasks.take();
                if (task == POISON_PILL) {
                    break;
                }
                task.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
