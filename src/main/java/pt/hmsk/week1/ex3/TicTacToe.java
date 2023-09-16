package pt.hmsk.week1.ex3;

import javax.swing.*;
import java.awt.*;

public class TicTacToe extends Grid {
    private String player = "X";
    public TicTacToe(int length) {
        super("TicTacToe", 3, 3, length);
        setGridStyle();
    }

    private void setGridStyle() {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                JLabel cell = getCell(row, col);
                cell.setFont(new Font("Arial", Font.BOLD, 42));
                cell.setHorizontalAlignment(SwingConstants.CENTER);
            }
            
        }
    }

    @Override
    public void callback() {
        play();
    }
    
    private void play() {
        JLabel cell = getCell(getLastClick()[0], getLastClick()[1]);
        if (cell.getText().isEmpty()) {
            cell.setText(player);
            if (!checkWinner()) {
                player = player.equals("X") ? "O" : "X";
            }
        }
    }

    private boolean checkWinner() {
        boolean winner = false;
        
        int rowPoints = 0;
        int colPoints = 0;
        int diag1Points = 0;
        int diag2Points = 0;
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                // Check rows
                if (getCell(row, col).getText().equals(player)) {
                    ++rowPoints;
                }
                // Check cols
                if (getCell(col, row).getText().equals(player)) {
                    ++colPoints;
                }
            }
            // Check main diagonal
            if (getCell(row, row).getText().equals(player)) {
                ++diag1Points;
            }
            // Check remaining diagonal
            if (getCell(row, 2 - row).getText().equals(player)) {
                ++diag2Points;
            }
            
            rowPoints = rowPoints == 3 ? 3 : 0;
            colPoints = colPoints == 3 ? 3 : 0;
        }
        winner = rowPoints == 3 || colPoints == 3 || diag1Points == 3 || diag2Points == 3;
        
        if (winner) {
            JOptionPane.showMessageDialog(null, "Player " + player + " Wins!");
        }
        
        return winner;
    }

    public static void main(String[] args) {
        new TicTacToe(100).open();
    }
}
