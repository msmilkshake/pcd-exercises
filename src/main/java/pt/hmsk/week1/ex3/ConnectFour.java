package pt.hmsk.week1.ex3;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class ConnectFour extends Grid {

    private ImageIcon redPlayer;
    private ImageIcon yellowPlayer;

    private Player turn = Player.RED;

    private enum Player {
        RED,
        YELLOW,
    }

    public ConnectFour(int length) {
        this(6, 7, length);
    }

    public ConnectFour(int rows, int cols, int length) {
        super("Connect Four", rows, cols, length);
        createPlayers(length);
    }

    @Override
    public void callback() {
        int col = getLastClick()[1];

        for (int row = getRowDim() - 1; row >= 0; --row) {
            JLabel cell = getCell(row, col);
            if (cell.getIcon() == null) {
                cell.setIcon(turn == Player.RED ? yellowPlayer : redPlayer);
                turn = turn == Player.RED ? Player.YELLOW : Player.RED;
                break;
            }
        }
    }

    private void createPlayers(int length) {
        redPlayer = createCircle(length, Color.RED);
        yellowPlayer = createCircle(length, Color.YELLOW);
    }

    private ImageIcon createCircle(int length, Color color) {
        int diameter = (int) (length * (4.0 / 5));
        BufferedImage circleImage = new BufferedImage(
                length,
                length,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = circleImage.createGraphics();

        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int topLeft = (int) (Math.round(1.0 * length / 2) -
                Math.round(1.0 * diameter / 2));
        float strokeWidth = diameter * 0.05f;
        Ellipse2D.Float circle =
                new Ellipse2D.Float(
                        topLeft - strokeWidth,
                        topLeft - strokeWidth / 2,
                        diameter,
                        diameter);
        Color[] colors = {color, color.darker()};
        float[] dist = {0.0f, 0.9f};
        RadialGradientPaint rgp = new RadialGradientPaint(
                new Point(length / 2, length / 2),
                1.0f * diameter / 2, dist, colors);

        g2d.setPaint(rgp);
        g2d.fill(circle);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(strokeWidth));
        g2d.draw(circle);
        g2d.dispose();

        return new ImageIcon(circleImage);
    }

    public static void main(String[] args) {
        new ConnectFour(50).open();
    }
}
