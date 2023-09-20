package pt.hmsk.week2.ex3;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Track extends JComponent implements Observer {
    private int numCars;
    private int numSteps;
    private int[] carPositions;
    private ImageIcon icon = new ImageIcon("assets/azul.gif");
    private int winner = -1;
    private boolean hasWinner = false;
    private Thread[] threads;
    private JFrame ownerFrame;

    public Track(int numCars, int numSteps, JFrame frame) {
        this.numCars = numCars;
        this.numSteps = numSteps;
        ownerFrame = frame;
        carPositions = new int[numCars];
    }

    private void moveCar(int car, int position) {
        if (car < 0 || car >= numCars)
            throw new IllegalArgumentException("invalid car index: " + car);
        if (position < 0 || position > numSteps)
            throw new IllegalArgumentException("invalid position: " + position);
        carPositions[car] = position;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        double deltaY = ((double) getHeight()) / (numCars + 1);
        double deltaX = ((double) getWidth() - icon.getIconWidth()) / numSteps;
        for (int i = 0; i < numCars; i++) {
            g.drawLine(0, (int) (deltaY * (i + 1)), getWidth(),
                    (int) (deltaY * (i + 1)));
            g.drawImage(icon.getImage(), (int) (carPositions[i] * deltaX),
                    (int) (deltaY * (i + 1)) - icon.getIconHeight(), null);
        }
    }

    @Override
    public void update(Observable car, Object arg) {
        Car updatedCar = (Car) car;
        moveCar(updatedCar.getId(), updatedCar.getPosition());
        // Redraw everything!
        invalidate();
        if (updatedCar.hasFinished() && !hasWinner) {
            winner = updatedCar.getId();
            String message = "Car #" + (updatedCar.getId() + 1) + " won the race!";
            System.out.println(message);
            hasWinner = true;
            JOptionPane.showMessageDialog(ownerFrame, message);
        }
        if (hasWinner) {
            stopAllCars();
        }
    }

    private void stopAllCars() {
        for (Thread t : threads) {
            if (!t.isInterrupted()) {
                t.interrupt();
            }
        }
    }

    public void setThreads(Thread[] threads) {
        this.threads = threads;
    }
}
