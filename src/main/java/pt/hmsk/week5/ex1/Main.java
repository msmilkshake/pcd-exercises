package pt.hmsk.week5.ex1;

import pt.hmsk.week4.ex1.BufferTable;
import pt.hmsk.week4.ex1.BufferTableNotify;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        v1();
    }
    
    public static void v1() throws InterruptedException {
        BufferTableLock table = new BufferTableLock();
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
}
