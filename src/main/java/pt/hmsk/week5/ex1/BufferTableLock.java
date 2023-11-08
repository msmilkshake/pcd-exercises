package pt.hmsk.week5.ex1;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferTableLock {
    public static final int CAPACITY = 10;
    
    private List<Boar> buffer = new LinkedList<>();
    private Lock lock = new ReentrantLock();
    private Condition bufferNotFull = lock.newCondition();
    private Condition bufferNotEmpty = lock.newCondition();

    /**
     * Condicao: So consigo inserir se a mesa nao estiver cheia (bufferNotFull)
     */
    public void put(Boar b) throws InterruptedException {
        lock.lock();
        try {
            while (buffer.size() >= CAPACITY) {
                bufferNotFull.await();
            }
            buffer.add(b);
            bufferNotEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Condicao: So consigo remover se a mesa nao estiver vazia (bufferNotEmpty)
     */
    public Boar get() throws InterruptedException {
        lock.lock();
        try {
            while(buffer.isEmpty()) {
                bufferNotEmpty.await();
            }
            Boar b = buffer.remove(0);
            bufferNotFull.signalAll();
            return b;
        } finally {
            lock.unlock();
        }
    }
}
