package pt.hmsk.week6.ex2.tiago;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadPool {
    private final int numWorkers;
    private final BlockingQueue<Runnable> taskQueue;
    private final List<Worker> workers;
    private final AtomicBoolean isShutdownInitiated;

    public ThreadPool(int numWorkers, int queueCapacity) {
        this.numWorkers = numWorkers;
        this.taskQueue = new BlockingQueue<>(queueCapacity);
        this.workers = new ArrayList<>();
        this.isShutdownInitiated = new AtomicBoolean(false);

        for (int i = 0; i < numWorkers; i++) {
            Worker worker = new Worker("ThreadPool-Worker-" + i);
            workers.add(worker);
            worker.start();
        }
    }

    public void submit(Runnable task) {
        if (!isShutdownInitiated.get()) {
            try {
                taskQueue.put(task);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted while submitting a task.");
            }
        } else {
            throw new IllegalStateException("ThreadPool is shutdown, cannot accept new tasks.");
        }
    }

    public void shutdown() {
        isShutdownInitiated.set(true);
    }

    public void shutdownNow(){
        shutdown();
        taskQueue.clear();
        workers.forEach(worker -> worker.interrupt());

    }


    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        long deadline = System.nanoTime() + unit.toNanos(timeout);
        for (Thread worker : workers) {
            long timeLeft = deadline - System.nanoTime();
            if (timeLeft <= 0L) return false;
            worker.join(timeLeft / 1_000_000L, (int) (timeLeft % 1_000_000L));
        }
        return true;
    }

    public boolean isShutdown() {
        return isShutdownInitiated.get();
    }

    public boolean isTerminated() {
        for (Worker worker : workers) {
            if (worker.isAlive()) {
                return false;
            }
        }
        return true;
    }

    private class Worker extends Thread {
        public Worker(String name) {
            super(name);
        }

        public void run() {
   //         while (!isShutdownInitiated.get() || !taskQueue.isEmpty()) {
                try {
                    while (true) {
                        if (isShutdownInitiated.get() && taskQueue.isEmpty()) {
                            break;
                        }
                        Runnable task = taskQueue.take();
                        task.run();
                    }
                } catch (InterruptedException e) {
                    if (isShutdownInitiated.get()) {
                        return;
                    }
                }

            System.out.println("Worker stopped.");
        }
    }

    // Main method to demonstrate ThreadPool usage
    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool(5, 10);

        for (int i = 0; i < 10; i++) {
            int taskNumber = i;
            threadPool.submit(() -> {
                System.out.println("Running task " + taskNumber + " on thread " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simulate some work by sleeping for 1 second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        threadPool.shutdown();
        if (!threadPool.awaitTermination(10, TimeUnit.SECONDS)) {
            System.out.println("Not all tasks finished before the timeout");
        } else {
            System.out.println("All tasks completed");
        }
    }
}
