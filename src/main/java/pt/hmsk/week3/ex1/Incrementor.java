package pt.hmsk.week3.ex1;

public class Incrementor extends Thread {
    private final Counter counter;
    private final int n;

    public Incrementor(Counter counter) {
        this(counter, 1000);
    }
    
    public Incrementor(Counter counter, int n) {
        this.counter = counter;
        this.n = n;
    }

    @Override
    public void run() {
        loop();
    }
    
    protected void loop() {
        for (int i = 0; i < n; ++i) {
            counter.increment();
        }
    }
}
