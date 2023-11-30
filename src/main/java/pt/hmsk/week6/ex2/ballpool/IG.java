package pt.hmsk.week6.ex2.ballpool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class IG {
    ArrayList<Bola> bolas = new ArrayList<>();
    ThreadPoolTiago threadPool = new ThreadPoolTiago(6, 10000);
    private JFrame janela;

    public void addContent() {
        janela = new JFrame("hh");
        janela.setLayout(new BorderLayout());
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        JButton start = getStartButton();
        JButton shutdown = getShutdownButton();
        JButton shutdownNow = getShutdownNowButton();

        buttonsPanel.add(start);
        buttonsPanel.add(shutdown);
        buttonsPanel.add(shutdownNow);
        janela.add(buttonsPanel, BorderLayout.SOUTH);
        janela.setSize(800, 600);
        janela.setVisible(true);
    }

    private JButton getShutdownButton() {
        JButton shutdown = new JButton("Shutdown");
        shutdown.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                threadPool.shutdown();
            }
        });
        return shutdown;
    }

    private JButton getShutdownNowButton() {
        JButton shutdownNow = new JButton("Shutdown Now");
        shutdownNow.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                threadPool.shutdownNow();
            }
        });
        return shutdownNow;
    }

    private JButton getStartButton() {
        JButton start = new JButton("Submit");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO criar ThreadPool. Aqui são simplesmente lançadas as threads.

                List<Bola> localBalls = new ArrayList<>();
                BallPainter painter = new BallPainter();
                janela.add(painter, BorderLayout.CENTER);


                for (int i = 0; i < 25; i++) {
                    Bola bola = new Bola();
                    bola.addObserver(painter);
                    localBalls.add(bola);
                    painter.addBall(bola);
                }
                janela.setVisible(true);

                for (Bola bola : localBalls) {
                    threadPool.submit(bola);
                }
            }
        });
        return start;
    }

    public static void main(String[] args) {
        new IG().addContent();
    }
}
