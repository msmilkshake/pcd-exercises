package pt.hmsk.week5.ex1;

import pt.hmsk.week4.ex1.BufferTable;

public class Eater extends Thread {
    private int id;
    private int max;
    private BufferTableLock table;

    public Eater(int id, int max, BufferTableLock table) {
        super("Eater-" + id);
        this.id = id;
        this.max = max;
        this.table = table;
    }


    @Override
    public void run() {
        int i = 0;
        try {
            if (max == 0) {
                while (true) {
                    Boar b = table.get();
                    System.out.println(b + " [ Consumed by: " + getName() + "]");
                    ++i;
                }
            }
            for (; i < max; ++i) {
                Boar b = table.get();
                System.out.println(b + " [ Consumed by: " + getName() + "]");
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " - Interrupted");
        }
        System.out.println(getName() + " consumed " + i + " Boars");
    }
}
