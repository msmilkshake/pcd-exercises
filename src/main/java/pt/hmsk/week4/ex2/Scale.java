package pt.hmsk.week4.ex2;

import javax.swing.*;

public class Scale {
    private double weight = 0.0;

    private JLabel display;

    public double getWeight() {
        return weight;
    }

    public void setDisplay(JLabel display) {
        this.display = display;
    }

    public synchronized void addGold(double amount) throws InterruptedException {
        while (weight >= 12.5) {
            wait();
        }
        weight += amount;
        display.setText("" + weight);
        notifyAll();
    }

    public synchronized void takeGold(double amount) throws InterruptedException {
        while (weight < amount) {
            wait();
        }
        weight -= amount;
        notifyAll();
    }
}
