package pt.hmsk.week4.ex1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        v1();
        System.out.println("--------------------------------");
    }
    
    public static void v1() throws InterruptedException {
        BufferTable table = new BufferTable();
        Thread[] cooks = new Thread[5];
        Thread[] eaters = new Thread[10];
        for (int i = 0; i < 5; ++i) {
            cooks[i] = new Cook(i, 10, table);
        }
        for (int i = 0; i < 10; ++i) {
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
}
