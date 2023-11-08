package pt.hmsk.week5.ex1;

import pt.hmsk.week4.ex1.BufferTable;

public class Cook extends Thread {
    private int id;
    private int max;
    private int sequence = 0;

    private BufferTableLock table;

    public Cook(int id, int max, BufferTableLock table) {
        super("Cook-" + id);
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
                    Boar b = new Boar(id, sequence++);
                    table.put(b);
                    ++i;
                }
            }
            for (; i < max; ++i) {
                Boar b = new Boar(id, sequence++);
                // System.out.println(getName() + " cooked " + b);
                table.put(b);
                // System.out.println(getName() + " served " + b + " to the table.");
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " - Interrupted");
        }
        System.out.println(getName() + " produced " + i + " Boars");
    }
}
