package pt.hmsk.week4.ex1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        v1();
        System.out.println("--------------------------------");
        v2();
        System.out.println("--------------------------------");
        // Creates deadlocks.
        v3();
    }
    
    public static void v1() throws InterruptedException {
        BufferTable table = new BufferTable();
        Thread[] cooks = new Thread[5];
        Thread[] eaters = new Thread[10];
        for (int i = 0; i < cooks.length; ++i) {
            cooks[i] = new Cook(i, 10, table);
        }
        for (int i = 0; i < eaters.length; ++i) {
            eaters[i] = new Eater(i, 5, table);
        }
        for (Thread cook : cooks) {
            cook.start();
        }
        for (Thread eater : eaters) {
            eater.start();
        }
        for (Thread cook : cooks) {
            cook.join();
        }
        for (Thread eater : eaters) {
            eater.join();
        }
    }

    public static void v2() throws InterruptedException {
        BufferTable table = new BufferTable();
        Thread[] cooks = new Thread[5];
        Thread[] eaters = new Thread[10];
        for (int i = 0; i < cooks.length; ++i) {
            cooks[i] = new Cook(i, 0, table);
        }
        for (int i = 0; i < eaters.length; ++i) {
            eaters[i] = new Eater(i, 0, table);
        }
        for (Thread cook : cooks) {
            cook.start();
        }
        for (Thread eater : eaters) {
            eater.start();
        }
        
        Thread.sleep(10000);
        for (Thread cook : cooks) {
            cook.interrupt();
        }
        for (Thread eater : eaters) {
            eater.interrupt();
        }
        for (Thread cook : cooks) {
            cook.join();
        }
        for (Thread eater : eaters) {
            eater.join();
        }
    }

    public static void v3() throws InterruptedException {
        BufferTableNotify table = new BufferTableNotify(1);
        Thread[] cooks = new Thread[2];
        Thread[] eaters = new Thread[2];
        for (int i = 0; i < cooks.length; ++i) {
            cooks[i] = new Cook(i, 100, table);
        }
        for (int i = 0; i < eaters.length; ++i) {
            eaters[i] = new Eater(i, 100, table);
        }
        for (Thread cook : cooks) {
            cook.start();
        }
        for (Thread eater : eaters) {
            eater.start();
        }
        for (Thread cook : cooks) {
            cook.join();
        }
        for (Thread eater : eaters) {
            eater.join();
        }
    }
}
