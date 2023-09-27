package pt.hmsk.week3.ex1;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // Part a) unsafe
        Counter counter = new Counter();
        incrementTest("Part a), unsafe counter", counter);
        
        // Part a) safe
        counter = new SafeCounter();
        incrementTest("Part a), safe counter", counter);
        
        // Part b) explicit lock
        counter = new CounterWithLock();
        incrementTest("Part b), explicit lock", counter);

        // Part c) atomic integer
        counter = new AtomicCounter();
        incrementTest("Part c), atomic counter", counter);
    }
    
    private static void incrementTest(String message, Counter counter) {
        Incrementor[] incrementors = {
                new Incrementor(counter),
                new Incrementor(counter),
                new Incrementor(counter),
                new Incrementor(counter)
        };
        for (Incrementor incrementor : incrementors) {
            incrementor.start();
        }
        try {
            for (Incrementor incrementor : incrementors) {
                incrementor.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(message + ": " + counter.getValue());
    }
    
}
