package pt.hmsk.week2.ex5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PongBoard extends JPanel {
    private Dimension minSize = new Dimension(480, 560);
    private Dimension playerSize = new Dimension(60, 10);
    
    private JPanel gameContent = new JPanel();
    private JLabel player1 = new JLabel();
    private JLabel player2 = new JLabel();
    
    private boolean aPressed = false;
    private boolean dPressed = false;
    private boolean lPressed = false;
    private boolean rPressed = false;
    
    private int speed = 6;
    
    public PongBoard() {
        addContent();
        gameLoop();
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
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        gameContent.setLayout(null);
        gameContent.setBackground(Color.LIGHT_GRAY);
        gameContent.setBorder(BorderFactory.createMatteBorder(0, 4, 0, 4, Color.black));
        gameContent.setPreferredSize(minSize);
        
        player1.setBackground(Color.BLUE);
        player1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        player1.setPreferredSize(playerSize);
        player1.setOpaque(true);
        
        player2.setBackground(Color.RED);
        player2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        player2.setPreferredSize(playerSize);
        player2.setOpaque(true);
        int x = minSize.width / 2 - 4 - playerSize.width / 2;
        int y1 = 10;
        int y2 = minSize.height - playerSize.height - 10;
        player1.setBounds(x, y1, 60, 10);
        player2.setBounds(x, y2, 60, 10);
        
        gameContent.add(player1);
        gameContent.add(player2);
        
        
        add(gameContent, gbc);
    }
    
    private void gameLoop() {
        Timer timer = new Timer(20, e -> {
            handleMovement();
            repaint();
        });
        timer.start();
    }
    
    private void handleMovement() {
        handlePlayerMovement(player1, aPressed, dPressed);
        handlePlayerMovement(player2, lPressed, rPressed);
    }

    private void handlePlayerMovement(JLabel player, boolean left, boolean right) {
        int x = player.getX();
        if (left && x > 4) {
            x -= speed;
            player.setLocation(x, player.getY());
        }
        if (right && x < minSize.width - playerSize.width - 4 - 24) {
            x += speed;
            player.setLocation(x, player.getY());
        }
    }

    public void handleKeyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A && !aPressed) {
            aPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D && !dPressed) {
            dPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && !lPressed) {
            lPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && !rPressed) {
            rPressed = true;
        }
    }

    public void handleKeyRelease(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A && aPressed) {
            aPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D && dPressed) {
            dPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && lPressed) {
            lPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && rPressed) {
            rPressed = false;
        }
    }
}
