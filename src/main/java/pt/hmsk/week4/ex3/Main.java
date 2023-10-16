package pt.hmsk.week4.ex3;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new BlockingQueue<>(3);
        Thread[] producers = new Thread[5];
        Thread[] consumers = new Thread[5];

        for (int i = 0; i < producers.length; ++i) {
            producers[i] = new Thread(() -> {
                Random r = new Random();
                int enqueues = 0;
                try {
                    while (true) {
                        int number = r.nextInt(10000);
                        int ms = r.nextInt(2500);
                        queue.put(number);
                        ++enqueues;
                        System.out.println(Thread.currentThread() + " enqueued number " + number +
                                " and will sleep for " + ms + "ms");
                        Thread.sleep(ms);
                    }
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread() + " was interrupted");
                }
                System.out.println(Thread.currentThread() + " enqueued " + enqueues + " numbers");
            }, "Producer --> #" + i);
        }

        for (int i = 0; i < consumers.length; ++i) {
            consumers[i] = new Thread(() -> {
                Random r = new Random();
                int dequeues = 0;
                try {
                    while (true) {
                        int number = queue.take();
                        ++dequeues;
                        int ms = r.nextInt(2500);
                        System.out.println(Thread.currentThread() + " dequeued number " + number +
                                " and will wait " + ms + "ms");
                        Thread.sleep(ms);
                    }
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread() + " was interrupted");
                }
                System.out.println(Thread.currentThread() + " dequeued " + dequeues + " numbers");
            }, "<-- Consumer #" + i);
        }
        
        for(Thread t : producers) {
            t.start();
        }
        for (Thread t : consumers) {
            t.start();
        }
        
        Thread.sleep(15_000);
        queue.clear();
        Thread.sleep(15_000);
        queue.clear();
        Thread.sleep(15_000);
        

        for(Thread t : producers) {
            t.interrupt();
        }
        for (Thread t : consumers) {
            t.interrupt();
        }
        for(Thread t : producers) {
            t.join();
        }
        for (Thread t : consumers) {
            t.join();
        }
    }
}
