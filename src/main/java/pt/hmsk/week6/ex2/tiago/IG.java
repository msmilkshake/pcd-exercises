package pt.hmsk.week6.ex2.tiago;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class IG {
    ArrayList<Bola> bolas = new ArrayList<>();
    ThreadPoolPaulo threadPool;

    public void addContent() {
        JFrame janela = new JFrame("hh");
        janela.setLayout(new BorderLayout());
        BallPainter painter = new BallPainter();
        janela.add(painter, BorderLayout.CENTER);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        threadPool = new ThreadPoolPaulo(6);


        JButton start = new JButton("Submit");
        start.addActionListener(new ActionListener() {

            // ExecutorService threadPool = Executors.newFixedThreadPool(6);

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO criar ThreadPool. Aqui são simplesmente lançadas as threads.
                
                List<Bola> localBalls = new ArrayList<>();
                for (int i = 0; i < 25; i++) {
                    Bola bola = new Bola();
                    bola.addObserver(painter);
                    localBalls.add(bola);
                    painter.addBall(bola);
                }

                for (Bola bola : localBalls) {
                    try {
                        threadPool.submit(bola);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }


            }
        });

        // Shutdown Button
        JButton shutdown = new JButton("Shutdown");
        shutdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (threadPool != null) {
                    threadPool.shutdown();
                }
            }
        });

        // ShutdownNow Button
        JButton shutdownNow = new JButton("ShutdownNow");
        shutdownNow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (threadPool != null) {
                    threadPool.shutdownNow();
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(start);
        buttonPanel.add(shutdown);
        buttonPanel.add(shutdownNow);

        janela.add(buttonPanel, BorderLayout.SOUTH);
        janela.setSize(800, 600);
        janela.setVisible(true);
    }

    public static void main(String[] args) {
        new IG().addContent();

    }

}
