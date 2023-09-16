package pt.hmsk.week1.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppExample {
    private JFrame frame = new JFrame("Example");

    private JLabel lbl1 = new JLabel("Label1");
    private JLabel lbl2 = new JLabel("Label2");
    private JLabel lbl3 = new JLabel("Label3");

    private JButton btn1 = new JButton("Button 1");
    private JButton btn2 = new JButton("Button 2");

    public AppExample() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(300, 200);
        addFrameContent();

        addListeners();

        frame.pack();

        frame.setVisible(true);
    }

    private void addFrameContent() {

        // BORDER LAYOUT
        //        frame.add(new JLabel("Label 1"), BorderLayout.NORTH);
        //        frame.add(new JLabel("Label 2"), BorderLayout.CENTER);
        //        frame.add(new JLabel("Label 3"), BorderLayout.SOUTH);

        // FLOW LAYOUT
        //        frame.setLayout(new FlowLayout());
        //        
        //        frame.add(new JLabel("Label 1"));
        //        frame.add(new JLabel("Label 2"));
        //        frame.add(new JLabel("Label 3"));

        // GRID LAYOUT
        //        frame.setLayout(new GridLayout());
        //
        //        frame.add(new JLabel("Label 1"));
        //        frame.add(new JLabel("Label 2"));
        //        frame.add(new JLabel("Label 3"));

        frame.setLayout(new GridLayout(2, 1));

        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(2, 1));

        frame.add(p1);
        frame.add(p2);

        p1.add(lbl1);
        p1.add(lbl2);
        p1.add(lbl3);

        p2.add(btn1);
        p2.add(btn2);

    }

    private void addListeners() {
        btn1.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame,"You clicked Button 1!");
        });
        
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,"You clicked Button 2!");
            }
        });
    }

    public static void main(String[] args) {
        new AppExample();
    }

}
