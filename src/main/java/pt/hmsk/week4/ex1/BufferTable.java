package pt.hmsk.week4.ex1;

import java.util.LinkedList;
import java.util.List;

public class BufferTable {
    private final int tableCapacity;
    private List<Boar> list = new LinkedList<>();

    public BufferTable() {
        tableCapacity = 10;
    }
    
    public BufferTable(int capacity) {
        tableCapacity = capacity;
    }
    
    public synchronized void put(Boar b) throws InterruptedException {
        while (list.size() >= tableCapacity) {
            wait();
        }
        list.add(b);
        notifyAll();
    }

    public synchronized Boar take() throws InterruptedException {
        while (list.isEmpty()) {
            wait();
        }
        Boar b = list.remove(0);
        notifyAll();
        return b;
    }
}
