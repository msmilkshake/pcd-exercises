package pt.hmsk.week2.ex5;

import javax.swing.*;

public class PongGame {
    JFrame frame = new JFrame("P0nG");
    
    public PongGame() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(new PongBoard());
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new PongGame();
    }
    
}
