package pt.hmsk.week6.ex2.ballpool;

import java.awt.*;
import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

public class Bola extends Observable implements DrawableBall, Runnable {
    private float estado = 0;
    private Color color = new Color((int) (Math.random() * 256),
            (int) (Math.random() * 256), (int) (Math.random() * 256));


    @Override
    public void run() {
        while (!bolaAtingiuLimite()) {
            // Increment the state [.01, .01]
            estado += ThreadLocalRandom.current().nextDouble(0.01, 0.1);
            if (estado > 1f) {
                estado = 1f;
            }
            setChanged();
            notifyObservers();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public boolean bolaAtingiuLimite() {
        return estado >= 1;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public float getX() {
        return estado;
    }

    @Override
    public int getSize() {
        return 10;
    }

}
