package pt.hmsk.week2.ex2;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GUI {
    private JFrame frame = new JFrame();
    private int distance;

    private enum State {
        UNSET,
        READY,
        RUNNING,
        STOPPED
    }

    private Horse[] horses = null;
    private JButton btnStart = new JButton("Iniciar");
    private JPanel horsesPanel = new JPanel(new GridLayout(1, 0));
    private State state = State.UNSET;

    public GUI() {
        frame.setTitle("Corrida");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setupFrameContent();
        registerActions();
    }

    private void registerActions() {
        btnStart.addActionListener(e -> {
            switch (state) {
                case READY:
                    startRace();
                    break;
                case RUNNING:
                    stopRace();
                    break;
                case STOPPED:
                    resetRace();
                    break;
            }
        });
    }

    private void startRace() {
        for (Horse horse : horses) {
            horse.start();
        }
        btnStart.setText("Interromper");
        state = State.RUNNING;
    }

    private void stopRace() {
        for (Horse horse : horses) {
            horse.interrupt();
        }
        try {
            for (Horse horse : horses) {
                if (!horse.isInterrupted()) {
                    horse.join();
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        btnStart.setText("Reiniciar");
        state = State.STOPPED;
    }

    private void resetRace() {
        horsesPanel.removeAll();
        initHorses();
        btnStart.setText("Iniciar");
        state = State.READY;
    }

    private void setupFrameContent() {
        frame.setLayout(new GridBagLayout());
        GridLayout mainLayout = new GridLayout(2, 1);
        mainLayout.setHgap(12);
        mainLayout.setVgap(6);

        JPanel mainPanel = new JPanel(mainLayout);
        mainPanel.setBorder(new EmptyBorder(6, 12, 6, 12));
        ((GridLayout) horsesPanel.getLayout()).setHgap(12);
        mainPanel.add(horsesPanel);

        mainPanel.add(btnStart);

        frame.add(mainPanel);
    }

    public void show() {
        if (state == State.UNSET) {
            throw new RuntimeException("To show the app, you must setup " +
                    "the horses by calling setHorses().");
        }
        frame.pack();
        frame.setVisible(true);
    }

    public GUI setHorses(int number) {
        return setHorses(number, 30);
    }

    public GUI setHorses(int number, int distance) {
        if (state != State.UNSET) {
            throw new RuntimeException("Horses can only be setup once.");
        }

        this.horses = new Horse[number];
        this.distance = distance;

        initHorses();

        state = State.READY;
        return this;
    }

    private void initHorses() {
        Horse.resetIdGenerator();
        for (int i = 0; i < horses.length; ++i) {
            horses[i] = new Horse(distance);
            horses[i].setOwner(this);
            horsesPanel.add(horses[i].getTextField());
        }
    }

    public void notifyDone() {
        boolean done = true;
        for (Horse h : horses) {
            done = done && h.hasFinished();
        }

        if (done) {
            stopRace();
        }
    }

    public static void main(String[] args) {
        new GUI().setHorses(9, 60).show();
    }
}
