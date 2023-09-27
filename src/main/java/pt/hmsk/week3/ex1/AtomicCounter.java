package pt.hmsk.week3.ex1;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter extends Counter {
    protected AtomicInteger value = new AtomicInteger(0);

    @Override
    public void increment() {
        value.incrementAndGet();
    }

    @Override
    public int getValue() {
        return value.get();
    }
}
