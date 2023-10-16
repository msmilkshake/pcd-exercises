package pt.hmsk.week4.ex2;

import java.util.Random;

public class GoldExtractor extends Thread {
    private static int idGenerator = 0;
    private Random r = new Random();
    
    private Scale scale;
    private int id;
    
    public GoldExtractor(Scale scale) {
        super("[GoldExtractor #" + idGenerator++ + "]");
        this.id = idGenerator;
        this.scale = scale;
    }

    @Override
    public void run() {
        System.out.println(currentThread() + " - run() start");
        try {
            while (true) {
                scale.addGold(r.nextDouble());
//                sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println(currentThread() + " - Interrupted");
        }
        System.out.println(currentThread() + " - run() end");
    }
}
