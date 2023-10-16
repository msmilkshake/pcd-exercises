package pt.hmsk.week4.ex2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUI {
    private JFrame frame;
    
    private JLabel lblScaleWeight;
    private JButton btnStop;
    
    private Scale scale = new Scale();
    private GoldExtractor extractor = new GoldExtractor(scale);
    private Smelter blacksmith = new Smelter(scale);
    private List<Thread> threads = new ArrayList<>();

    public GUI() {
        frame = new JFrame("Gold Ingot Operation");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addFrameContent();
        registerActions();
        
        init();

        frame.pack();
    }

    private void init() {
        threads.add(extractor);
        threads.add(blacksmith);
        for (Thread t : threads) {
            t.start();
        }
    }

    private void registerActions() {
        scale.setDisplay(lblScaleWeight);
        btnStop.addActionListener((e) -> {
            for (Thread t : threads) {
                t.interrupt();
            }
        });
    }

    private void addFrameContent() {
        GridLayout layout = new GridLayout(2, 1);
        frame.setLayout(layout);
        
        FlowLayout labelsLayout = new FlowLayout();
        JPanel labelsPanel = new JPanel(labelsLayout);
        
        frame.add(labelsPanel);

        JLabel text = new JLabel("Gold weight on the scale: ");
        lblScaleWeight = new JLabel("" + scale.getWeight());

        labelsPanel.add(text);
        labelsPanel.add(lblScaleWeight);
        
        btnStop = new JButton("STOP");
        frame.add(btnStop);

    }


    public void open() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI().open();
    }
}
