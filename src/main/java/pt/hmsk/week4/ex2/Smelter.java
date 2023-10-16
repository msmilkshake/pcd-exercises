package pt.hmsk.week4.ex2;

public class Smelter extends Thread {
    private static int idGenerator = 0;
    
    private Scale scale;
    private int id;

    private double neededWeight = 12.5;
    private int ingotsSmelted = 0;

    public Smelter(Scale scale) {
        super("[Smelter #" + idGenerator++ + "]");
        id = idGenerator;
        this.scale = scale;
    }

    @Override
    public synchronized void run() {
        System.out.println(currentThread() + " - run() start");
        try {
            while (true) {
                scale.takeGold(12.5);

                System.out.println(currentThread() + " - Sleepig for 3000ms");
                sleep(3000);
                System.out.println(currentThread() + " - Sleep end");
                ++ingotsSmelted;
            }
        } catch (InterruptedException e) {
            System.out.println(currentThread() + " - Interrupted");
        }
        System.out.println("The Smelter smelted " + ingotsSmelted + " Gold ingots.");
        System.out.println(currentThread() + " - run() end");
    }
}
