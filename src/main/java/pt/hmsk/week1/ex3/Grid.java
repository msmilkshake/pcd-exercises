package pt.hmsk.week1.ex3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Grid {
    private JFrame frame = new JFrame();
    private JLabel[][] cells;

    private int[] lastClick = new int[]{-1, -1};

    public Grid(String name, int rows, int cols, int length) {
        validateArgs(rows, cols, length);
        frame.setTitle(name);

        buildGrid(rows, cols, length);

        frame.pack();
    }

    private void buildGrid(int rows, int cols, int length) {
        cells = new JLabel[rows][cols];
        Dimension square = new Dimension(length, length);
        frame.setLayout(new GridLayout(rows, cols));

        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                JLabel cell = buildCell(square);
                cells[row][col] = cell;
                registerAction(cell, row, col);
                
                frame.add(cell);
            }
        }
    }

    private void registerAction(JLabel cell, int row, int col) {
        cell.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lastClick[0] = row;
                lastClick[1] = col;
                callback();
            }
        });
    }

    public void callback() {
        JOptionPane.showMessageDialog(null,
                "click on label " + lastClick[0] + "," + lastClick[1]);
    }

    private JLabel buildCell(Dimension square) {
        JLabel cell = new JLabel();
        cell.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        cell.setPreferredSize(square);
        return cell;
    }

    public void validateArgs(int rows, int cols, int length) {
        if (rows <= 0) {
            throw new RuntimeException("Number of rows must be a positive int.");
        }
        if (cols <= 0) {
            throw new RuntimeException("Number of cols must be a positive int.");
        }
        if (length <= 0) {
            throw new RuntimeException("Length of cells must be a positive int.");
        }
    }

    public void open() {
        frame.setVisible(true);
    }

    public int[] getLastClick() {
        return lastClick;
    }
    
    public int getRowDim() {
        return cells.length;
    }

    public int cetColDim() {
        return cells[0].length;
    }
    

    public JLabel getCell(int row, int col) {
        return cells[row][col];
    }

    public static void main(String[] args) {
        new Grid("Test", 5, 4, 50).open();
    }
}
