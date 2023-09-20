package pt.hmsk.week2.ex2;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Horse extends Thread {
    private static int idGenerator = 0;
    private static final Random r = new Random();

    private final int id;
    private final JTextField textField = new JTextField();
    private GUI owner;
    
    private int currentDistance;
    
    
    public Horse(int distance) {
        this.id = ++idGenerator;
        this.currentDistance = distance;
        updateText();
        
        Dimension defaultSize = textField.getPreferredSize();
        defaultSize.setSize(30, defaultSize.height);
        textField.setPreferredSize(defaultSize);
        textField.setHorizontalAlignment(JTextField.CENTER);
    }

    public Horse() {
        this(30);
    }
    

    @Override
    public void run() {
        try {
            while (currentDistance > 0) {
                raceTick();
            }
            System.out.println("Horse #" + id + " finished the race.");
            textField.setBackground(Color.GREEN);
            owner.notifyDone();
        } catch (InterruptedException e) {
            System.out.println("Horse #" + id + " interrupted.");
        }
    }

    public void raceTick() throws InterruptedException {
        sleep(r.nextInt(201) + 10);
        --currentDistance;
        updateText();
    }

    private void updateText() {
        textField.setText("" + currentDistance);
    }

    public boolean hasFinished() {
        return currentDistance == 0;
    }
    
    public void setOwner(GUI owner) {
        this.owner = owner;
    }
    
    public JTextField getTextField() {
        return textField;
    }
    
    public static void resetIdGenerator() {
        idGenerator = 0;
    }
}
