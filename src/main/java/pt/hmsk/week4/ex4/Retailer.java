package pt.hmsk.week4.ex4;

public class Retailer extends Thread {
    private int id;
    private Distributor distributor;
    private int acquiredbatches = 0;
    
    public Retailer(int id, Distributor distributor) {
        super("[Retailer-" + id + "]");
        this.id = id;
        this.distributor = distributor;
    }

    @Override
    public void run() {
        try {
            while (true) {
                distributor.buy();
                ++acquiredbatches;
                sleep(10);
            }
        } catch (InterruptedException e) {
            System.out.println(currentThread() + " was Interrupted");
        }
        System.out.println(currentThread() + " acquired a total of " + acquiredbatches +
                " batches");
    }
}
