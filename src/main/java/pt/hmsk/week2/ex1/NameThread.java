package pt.hmsk.week2.ex1;

import java.util.Random;

public class NameThread extends Thread {
    public static Random r = new Random();

    private int part = 1;

    public NameThread() {
    }

    public NameThread(int part) {
        this.part = part;
    }

    @Override
    public void run() {
        if (part == 3) {
            part3Run();
        } else {
            part1And2Run();
        }
    }

    private void part1And2Run() {
        for (int i = 0; i < 10; ++i) {
            try {
                System.out.println(getId());
                sleep(r.nextInt(1000) + 1001);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void part3Run() {
        try {
            while (true) {
                System.out.println(getId());
                sleep(r.nextInt(1000) + 1001);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + getId() + " was interrupted.");
        }
    }

    public static void main(String[] args) {
        part3();
    }

    public static void part2() {
        Thread t1 = new NameThread();
        Thread t2 = new NameThread();

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void part3() {
        Thread t1 = new NameThread(3);
        Thread t2 = new NameThread(3);
        t1.start();
        t2.start();
        
        try {
            sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        
        t1.interrupt();
        t2.interrupt();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        
        System.out.println("Done.");
    }
}
