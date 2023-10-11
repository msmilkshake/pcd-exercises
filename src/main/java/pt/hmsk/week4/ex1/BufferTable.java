package pt.hmsk.week4.ex1;

import java.util.LinkedList;
import java.util.List;

public class BufferTable {
    private static final int tableCapacity = 10;
    private List<Boar> list = new LinkedList<>();
    
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
