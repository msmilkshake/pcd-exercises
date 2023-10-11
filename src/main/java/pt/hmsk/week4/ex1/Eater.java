package pt.hmsk.week4.ex1;

public class Eater extends Thread {
    private int id;
    private int max;
    private BufferTable table;

    public Eater(int id, int max, BufferTable table) {
        super("Eater-" + id);
        this.id = id;
        this.max = max;
        this.table = table;
    }


    @Override
    public void run() {
        int i = 0;
        try {
            for (; i < max; ++i) {
                Boar b = table.take();
                System.out.println(b + " [ Consumed by: " + getName() + "]");
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " - Interrupted");
        }
        System.out.println(getName() + " consumed " + i + " Boars");
    }
}
