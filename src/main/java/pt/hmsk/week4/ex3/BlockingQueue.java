package pt.hmsk.week4.ex3;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BlockingQueue <E> {
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
        if (capacity > 0) {
            notifyAll();
        }
    }
    
    public synchronized E take() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("[# QUEUE #] The queue is EMPTY");
            wait();
        }
        E head = queue.poll();
        System.out.println("[# QUEUE #] Item:" + head + " was removed");
        notifyAll();
        return head;
    }
    
    public synchronized int size() {
        return queue.size();
    }
    
    public synchronized void clear() {
        queue.clear();
        System.out.println("[# QUEUE #] --- CLEARED ---");
        notifyAll();
    }
}
