package pt.hmsk.week2.ex3;

import java.util.Observable;
import java.util.Random;

public class Car
        extends Observable
        implements Runnable {
    
    private static Random r = new Random();
    
    private int id;
    private int limit;
    private int position = 0;

    public int getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public Car(int id, int limit) {
        super();
        this.id = id;
        this.limit = limit;
    }


    @Override
    public void run() {
        try {
            while (position < limit) {
                raceTick();
            }
        } catch (InterruptedException ignore) {
        }
    }

    public void raceTick() throws InterruptedException {
        Thread.sleep(r.nextInt(201) + 10);
        ++position;
        setChanged();
        notifyObservers();
    }

    public boolean hasFinished() {
        return position == limit;
    }
}
