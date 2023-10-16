package pt.hmsk.week4.ex4;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUI {
    private JFrame frame;

    private JTextField txtProducts;
    private JButton btnStop;

    private Distributor distributor = new Distributor();

    private Supplier supplier = new Supplier(0, distributor);
    private Retailer[] retailers = new Retailer[5];

    public GUI() {
        frame = new JFrame("Retail Distribution Chain");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addFrameContent();
        registerActions();

        init();

        frame.pack();
    }

    private void init() {
        distributor.setTxtProducts(txtProducts);

        for (int i = 0; i < retailers.length; ++i) {
            retailers[i] = new Retailer(i, distributor);
        }

        for (Retailer r : retailers) {
            r.start();
        }
        supplier.start();
    }

    private void registerActions() {
        btnStop.addActionListener((e) -> {
            try {
                for (Retailer r : retailers) {
                    r.interrupt();
                }
                for (Retailer r : retailers) {
                    r.join();
                }
                supplier.interrupt();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void addFrameContent() {
        GridLayout layout = new GridLayout(2, 1);
        frame.setLayout(layout);

        FlowLayout labelsLayout = new FlowLayout();
        JPanel labelsPanel = new JPanel(labelsLayout);

        frame.add(labelsPanel);

        JLabel text = new JLabel("Products list: ");
        txtProducts = new JTextField(distributor.getProducts().toString());
        txtProducts.setPreferredSize(new Dimension(150, 50));

        labelsPanel.add(text);
        labelsPanel.add(txtProducts);

        btnStop = new JButton("STOP");
        frame.add(btnStop);

    }


    public void open() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new pt.hmsk.week4.ex4.GUI().open();
    }
}