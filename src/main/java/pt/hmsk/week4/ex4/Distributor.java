package pt.hmsk.week4.ex4;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class Distributor {
    private int minBatch = 10;
    
    private List<Integer> products = new LinkedList<>();
    private JTextField txtProducts;
    
    public Distributor() {
    }
    
    public synchronized void supply(int product) {
        products.add(product);
        txtProducts.setText(products.toString());
        notifyAll();
    }
    
    public synchronized List<Integer> buy() throws InterruptedException {
        while (products.size() < minBatch) {
            wait();
        }
        List<Integer> batch = products.subList(0, products.size());
        products.clear();
        txtProducts.setText(products.toString());
        return batch;
    }

    public void setTxtProducts(JTextField txtProducts) {
        this.txtProducts = txtProducts;
    }

    public synchronized List<Integer> getProducts() {
        return products;
    }
}
