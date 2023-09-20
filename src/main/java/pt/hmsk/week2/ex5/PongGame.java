package pt.hmsk.week2.ex5;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PongGame {
    private JFrame frame = new JFrame("P0nG");
    private PongBoard pb = new PongBoard();
    
    public PongGame() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(pb);
        setupKeyListener();
        frame.pack();
        frame.setVisible(true);
    }

    private void setupKeyListener() {
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                pb.handleKeyPress(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                pb.handleKeyRelease(e);
            }
        });
        frame.setFocusable(true);
        frame.requestFocusInWindow();
    }

    public static void main(String[] args) {
        new PongGame();
    }
}
