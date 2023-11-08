package pt.hmsk.week6.ex2.ballpool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IG {
    ArrayList<Bola> bolas = new ArrayList<>();

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

        JButton start = new JButton("Start");
        start.addActionListener(new ActionListener() {
            
			ExecutorService threadPool = Executors.newFixedThreadPool(6);
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO criar ThreadPool. Aqui são simplesmente lançadas as threads.
                for (Bola bola : bolas)
                    threadPool.submit(bola);


            }
        });
        janela.add(start, BorderLayout.SOUTH);
        janela.setSize(800, 600);
        janela.setVisible(true);
    }

    public static void main(String[] args) {
        new IG().addContent();

    }

}
