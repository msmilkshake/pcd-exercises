package pt.hmsk.week2.ex4;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GUI {
    JFrame frame = new JFrame("RNG");
    JButton button = new JButton("Stop");
    Thread threadA;
    Thread threadB;

    public GUI() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initThreads();
        addContent();
        registerActions();
    }

    private void initThreads() {
        Random r = new Random();
        threadA = new Thread("Thread A") {
            private long counter = 0;
            
            @Override
            public void run() {
                while (true) {
                    System.out.println(r.nextInt(9000) + 1000);
                    ++counter;
                    if (interrupted()) {
                        break;
                    }
                }
                System.out.println(this.getName() + " generated " + counter + " numbers.");
            }
        };

        threadB = new Thread("Thread B") {
            private long counter = 0;
            
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println(r.nextInt(9) + 1);
                        ++counter;
                        sleep(500);
                    }
                } catch (InterruptedException ignore) {
                }
                System.out.println(this.getName() + " generated " + counter + " numbers.");
            }
        };
    }

    private void registerActions() {
        button.addActionListener(e -> {
            if (!threadA.isInterrupted()) {
                threadA.interrupt();
            }
            if (!threadB.isInterrupted()) {
                threadB.interrupt();
            }
        });
    }

    public void show() {
        frame.pack();
        frame.setVisible(true);
        threadA.start();
        threadB.start();
    }

    private void addContent() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(320, 240));

        panel.setLayout(new GridBagLayout());
        panel.add(button);

        frame.add(panel);
    }

    public static void main(String[] args) {
        new GUI().show();
    }
}
