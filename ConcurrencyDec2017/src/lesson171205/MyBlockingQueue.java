package lesson171205;

import java.util.LinkedList;
import java.util.Queue;

public class MyBlockingQueue<T> {
    private final Object mutex = new Object();
    private Queue<T> tasks = new LinkedList<>();

    public void put(T task) {
        synchronized (mutex) {
            tasks.offer(task);
            mutex.notify();
        }
    }

    public T take() {
        T task = null;
        synchronized (mutex) {
            while (tasks.isEmpty()) {
                try {
                    mutex.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            task = tasks.poll();
        }
        return task;
    }
}
