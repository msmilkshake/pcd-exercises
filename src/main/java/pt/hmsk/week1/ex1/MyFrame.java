package pt.hmsk.week1.ex1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MyFrame {
    private JFrame frame = new JFrame("Hello");
    
    private JTextField titleField = new JTextField(frame.getTitle());
    private JTextField widthField = new JTextField();
    private JTextField heightField = new JTextField();
    
    private JButton updateButton = new JButton("update");
    private JCheckBox centerCheck = new JCheckBox("center");
    
    private final int MIN_WIDTH;
    private final int MIN_HEIGHT;
    
    public MyFrame() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addFrameConent();
        
        frame.pack();
        
        MIN_WIDTH = frame.getWidth();
        MIN_HEIGHT = frame.getHeight();
        
        widthField.setText("" + MIN_WIDTH);
        heightField.setText("" + MIN_HEIGHT);
        
        frame.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));

        registerActions();
    }

    private void addFrameConent() {
        frame.setLayout(new GridBagLayout());
        JPanel p1 = new JPanel();
        p1.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        GridLayout layout = new GridLayout(4, 2);
        layout.setHgap(12);
        layout.setVgap(6);
        p1.setLayout(layout);
        frame.add(p1);
        
        p1.add(new JLabel("title"));
        p1.add(titleField);
        
        p1.add(new JLabel("width"));
        p1.add(widthField);
        
        p1.add(new JLabel("height"));
        p1.add(heightField);
        
        p1.add(updateButton);
        p1.add(centerCheck);
    }

    private void registerActions() {
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateFieldDimensions();
            }
        });
        
        updateButton.addActionListener(e -> setWindowDimension());
    }
    
    public void open() {
        frame.setVisible(true);
    }
    
    public void setWindowDimension() {
        frame.setTitle(titleField.getText());
        try {
            int w = Integer.parseInt(widthField.getText());
            int h = Integer.parseInt(heightField.getText());
            if (w < MIN_WIDTH || h < MIN_HEIGHT) {
                throw new Exception();
            }
            frame.setSize(w, h);
        } catch (Exception ignore) {}
        if (centerCheck.isSelected()) {
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (int) (d.getWidth() / 2 - frame.getWidth() / 2);
            int y = (int) (d.getHeight() / 2 - frame.getHeight() / 2);
            frame.setLocation(x, y);
            
            // Also centers the frame
            frame.setLocationRelativeTo(null);
        }
    }
    
    public void updateFieldDimensions() {
        widthField.setText("" + frame.getWidth());
        heightField.setText("" + frame.getHeight());
    }

    public static void main(String[] args) {
        new MyFrame().open();
    }

}
