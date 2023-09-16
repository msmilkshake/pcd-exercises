package pt.hmsk.week1.ex2;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ImageFrame {
    private JFrame frame = new JFrame();

    private Dimension minSize = new Dimension(320, 240);

    private JButton previousBtn = new JButton("<");
    private JButton nextBtn = new JButton(">");
    private JButton updateBtn = new JButton("update");
    private JLabel imagePlaceholder = new JLabel();


    private String imagesFolder;
    private File[] images;

    private int currentImageIndex;


    public ImageFrame(String[] args) {

        handleArgs(args);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addFrameConent();

        frame.pack();

        registerActions();
    }

    private void addFrameConent() {
        imagePlaceholder.setSize(minSize);
        imagePlaceholder.setHorizontalAlignment(JLabel.CENTER);
        imagePlaceholder.setVerticalAlignment(JLabel.CENTER);

        frame.setLayout(new BorderLayout(12, 6));
        frame.add(previousBtn, BorderLayout.WEST);
        frame.add(nextBtn, BorderLayout.EAST);
        frame.add(updateBtn, BorderLayout.SOUTH);
        frame.add(imagePlaceholder, BorderLayout.CENTER);
    }

    private void registerActions() {
        previousBtn.addActionListener(e -> {
            currentImageIndex = Math.max(-1, currentImageIndex - 1);
            updateImage();
        });

        nextBtn.addActionListener(e -> {
            currentImageIndex = Math.min(images.length, currentImageIndex + 1);
            updateImage();
        });

        updateBtn.addActionListener(e -> refreshImages());
    }

    public void open() {
        frame.setVisible(true);
    }

    private void handleArgs(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("No path to images provided.");
        }
        imagesFolder = args[0];
        refreshImages();
    }

    private void refreshImages() {
        String imgRgx = ".+\\.(?>jpeg|jpg|png|gif|bmp|tif|tiff)\\b";
        images = new File(imagesFolder)
                .listFiles(pathname ->
                        pathname.getPath().toLowerCase().matches(imgRgx));
        if (images == null) {
            throw new RuntimeException("The provided images path doesn't exist.");
        }

        currentImageIndex = images.length > 0 ? 0 : -1;
        updateImage();
    }

    private void updateImage() {
        // Handle images list's boundaries
        if (currentImageIndex < 0 || currentImageIndex >= images.length) {
            imagePlaceholder.setIcon(null);
            imagePlaceholder.setText("Fim das imagens ☹\uFE0F");
            return;
        }
        
        // Load the selected image
        ImageIcon icon = new ImageIcon(images[currentImageIndex].getPath());

        // Scale the image to fit the label
        double scaleFactor = 1.0 * icon.getIconWidth() / minSize.getWidth();
        scaleFactor = Math.max(scaleFactor, 1.0 * icon.getIconHeight() / minSize.getHeight());
        Image scaled = icon.getImage()
                .getScaledInstance(
                        (int) (icon.getIconWidth() / scaleFactor),
                        (int) (icon.getIconHeight() / scaleFactor),
                        Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaled);
        int hgap = (int) (minSize.getWidth() - icon.getIconWidth()) / 2;
        int vgap = (int) (minSize.getHeight() - icon.getIconHeight()) / 2;
        
        // Set the image in the label
        imagePlaceholder.setText(null);
        imagePlaceholder.setIcon(icon);
        imagePlaceholder.setBorder(
                BorderFactory.createEmptyBorder(vgap, hgap, vgap, hgap));
    }

    public static void main(String[] args) {
        new ImageFrame(args).open();
    }

}
