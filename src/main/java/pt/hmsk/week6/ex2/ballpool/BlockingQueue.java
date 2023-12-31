package pt.hmsk.week6.ex2.ballpool;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<E> {
    private Queue<E> queue = new LinkedList<>();
    private int capacity = 0;

    public BlockingQueue() {
    }

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(E element) throws InterruptedException {
        while (capacity > 0 && queue.size() >= capacity) {
            System.out.println("[# QUEUE #] The queue is FULL");
            wait();
        }
        queue.add(element);
        System.out.println("[# QUEUE #] Item:" + element + " was added");
        notifyAll();
    }

    public synchronized E take() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Trying to take...");
            wait();
            System.out.println("I was released.");
        }
        E head = queue.poll();
        notifyAll();
        return head;
    }

    public synchronized int size() {
        return queue.size();
    }

    public synchronized void clear() {
        queue.clear();
        notifyAll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
