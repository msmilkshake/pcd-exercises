package pt.hmsk.week4.ex4;

import java.util.Random;

public class Supplier extends Thread {
    private int id;
    private Distributor distributor;
    private Random r = new Random();
    
    public Supplier(int id, Distributor distributor) {
        super("[Supplier-" + id + "]");
        this.id = id;
        this.distributor = distributor;
    }
    

    @Override
    public void run() {
        try {
            while (true) {
                int product = r.nextInt(10);
                distributor.supply(product);
                sleep(10);
                if (interrupted()) {
                    throw new InterruptedException();
                }
            }
        } catch (InterruptedException e) {
            System.out.println(currentThread() + " was Interrupted");
        }
        System.out.println(currentThread() + " Stopped supplying");
    }
}
