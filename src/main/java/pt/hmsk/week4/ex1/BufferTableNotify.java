package pt.hmsk.week4.ex1;

import java.util.LinkedList;
import java.util.List;

public class BufferTableNotify extends BufferTable {
    private final int tableCapacity;
    private List<Boar> list = new LinkedList<>();

    public BufferTableNotify() {
        tableCapacity = 10;
    }
    
    public BufferTableNotify(int capacity) {
        tableCapacity = capacity;
    }
    
    @Override
    public synchronized void put(Boar b) throws InterruptedException {
        while (list.size() >= tableCapacity) {
            wait();
        }
        list.add(b);
        notify();
    }

    @Override
    public synchronized Boar take() throws InterruptedException {
        while (list.isEmpty()) {
            wait();
        }
        Boar b = list.remove(0);
        notify();
        return b;
    }
}
