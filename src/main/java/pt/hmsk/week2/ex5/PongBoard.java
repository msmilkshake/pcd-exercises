package pt.hmsk.week2.ex5;

import javax.swing.*;
import java.awt.*;

public class PongBoard extends JPanel {
    private Dimension minSize = new Dimension(640, 800);
    JPanel gameContent = new JPanel();
    
    public PongBoard() {
        addContent();
    }

    private void addContent() {
        setMinimumSize(minSize);
        setPreferredSize(minSize);
        setMaximumSize(minSize);
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 12, 0, 12);
        gbc.weightx = 1.0;
        
        gameContent.setLayout(new GridBagLayout());
        gameContent.setBackground(Color.BLACK);
        
        add(gameContent, gbc);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
