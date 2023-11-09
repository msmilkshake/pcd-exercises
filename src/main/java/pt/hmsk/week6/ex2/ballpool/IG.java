package pt.hmsk.week6.ex2.ballpool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class IG {
    ArrayList<Bola> bolas = new ArrayList<>();
    ThreadPool threadPool = new ThreadPool(4);

    public void addContent() {
        JFrame janela = new JFrame("hh");
        janela.setLayout(new BorderLayout());
        BallPainter painter = new BallPainter();
        janela.add(painter, BorderLayout.CENTER);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 0; i < 25; i++) {
            Bola bola = new Bola();
            bola.addObserver(painter);
            bolas.add(bola);
            painter.addBall(bola);
        }
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
        JButton start = new JButton("Start");
        start.addActionListener(new ActionListener() {
            
            @Override
            
            public void actionPerformed(ActionEvent e) {
                // TODO criar ThreadPool. Aqui são simplesmente lançadas as threads.
                
                new Thread(() -> {
                    for (Bola bola : bolas) {
                        try {
                            threadPool.submit(bola);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }).start();
            }
        });
        return start;
    }

    public static void main(String[] args) {
        new IG().addContent();
    }

}
