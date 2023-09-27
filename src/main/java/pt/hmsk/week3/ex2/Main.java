package pt.hmsk.week3.ex2;

import java.util.Random;

public class Main {

    static class ThreadManipulator extends Thread {
        private Random r = new Random();
        private int val;

        Queue<Integer> queue;

        public ThreadManipulator(Queue<Integer> q, int val) {
            queue = q;
            this.val = val + 1;
        }

        @Override
        public void run() {
            for (int i = 0; i < 4000; ++i) {
                queue.offer(val);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; ++i) {
            testUnsafeQueue();
            testSafeQueue();
        }
    }

    public static void testUnsafeQueue() throws InterruptedException {
        Queue<Integer> q = new UnsafeQueue<>(24_000, 0.0);
        Thread[] threads = new Thread[6];
        for (int i = 0; i < 6; ++i) {
            threads[i] = new ThreadManipulator(q, i);
            threads[i].start();
        }
        for (int i = 0; i < 6; ++i) {
            threads[i].join();
        }
        System.out.println(q);
        System.out.println("Elements in the unsafe queue: " + q.size());
    }

    public static void testSafeQueue() throws InterruptedException {
        Queue<Integer> q = new SafeQueue<>(24_000, 0.0);
        Thread[] threads = new Thread[6];
        for (int i = 0; i < 6; ++i) {
            threads[i] = new ThreadManipulator(q, i);
            threads[i].start();
        }
        for (int i = 0; i < 6; ++i) {
            threads[i].join();
        }
        System.out.println("Elements in the safe queue: " + q.size());
    }
}
