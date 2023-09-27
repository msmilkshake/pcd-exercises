package pt.hmsk.week3.ex1;

public class SafeCounter extends Counter {

    @Override
    public synchronized void increment() {
        ++value;
    }

    @Override
    public synchronized int getValue() {
        return value;
    }
}
