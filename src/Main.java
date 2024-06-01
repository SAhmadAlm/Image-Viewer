import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.awt.image.RescaleOp;

class ImageViewerGUI extends JFrame implements ActionListener{
    JButton selectFileButton;
    JButton showImageButton;
    JButton resizeButton;
    JButton grayscaleButton;
    JButton brightnessButton;
    JButton closeButton;
    JButton showResizeButton;
    JButton showBrightnessButton;
    JButton backButton;
    JTextField widthTextField;
    JTextField heightTextField;
    JTextField brightnessTextField;
    String filePath = "C:/Users/SAAle/Downloads/Picture";
    File file;
    JFileChooser fileChooser = new JFileChooser(filePath);
    int h = 900;
    int w = 1200;
    float brightenFactor = 1;

    ImageViewerGUI(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Image Viewer");
        this.setSize(700, 300);
        this.setVisible(true);
        this.setResizable(true);

        mainPanel();
    }

    public void mainPanel(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 2));

        selectFileButton = new JButton("Choose Image");
        selectFileButton.addActionListener(this);
        showImageButton = new JButton("Show Image");
        showImageButton.addActionListener(this);
        brightnessButton = new JButton("Brightness");
        brightnessButton.addActionListener(this);
        grayscaleButton = new JButton("Grayscale");
        grayscaleButton.addActionListener(this);
        resizeButton = new JButton("Resize");
        resizeButton.addActionListener(this);
        closeButton = new JButton("Exit");
        closeButton.addActionListener(this);

        buttonsPanel.add(selectFileButton);
        buttonsPanel.add(showImageButton);
        buttonsPanel.add(brightnessButton);
        buttonsPanel.add(grayscaleButton);
        buttonsPanel.add(resizeButton);
        buttonsPanel.add(closeButton);

        buttonsPanel.setBounds(100, 50, 500, 150);
        mainPanel.add(buttonsPanel);
        this.add(mainPanel);
    }

    public void resizePanel(){
        JPanel resizePanel = new JPanel();
        resizePanel.setLayout(null);

        JLabel widthLabel = new JLabel("Width:");
        widthLabel.setBounds(50, 50, 100, 30);
        resizePanel.add(widthLabel);

        widthTextField = new JTextField();
        widthTextField.setBounds(150, 50, 100, 30);
        resizePanel.add(widthTextField);

        JLabel heightLabel = new JLabel("Height:");
        heightLabel.setBounds(50, 100, 100, 30);
        resizePanel.add(heightLabel);

        heightTextField = new JTextField();
        heightTextField.setBounds(150, 100, 100, 30);
        resizePanel.add(heightTextField);

        showResizeButton = new JButton("Result");
        showResizeButton.addActionListener(this);
        showResizeButton.setBounds(150, 150, 100, 30);
        resizePanel.add(showResizeButton);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        backButton.setBounds(50, 150, 100, 30);
        resizePanel.add(backButton);

        this.add(resizePanel);
    }

    public void brightnessPanel(){
        JPanel brightnessPanel = new JPanel();
        brightnessPanel.setLayout(null);

        JLabel brightnessLabel = new JLabel("Brightness (0-1):");
        brightnessLabel.setBounds(50, 50, 150, 30);
        brightnessPanel.add(brightnessLabel);

        brightnessTextField = new JTextField();
        brightnessTextField.setBounds(200, 50, 100, 30);
        brightnessPanel.add(brightnessTextField);

        showBrightnessButton = new JButton("Result");
        showBrightnessButton.addActionListener(this);
        showBrightnessButton.setBounds(150, 100, 100, 30);
        brightnessPanel.add(showBrightnessButton);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        backButton.setBounds(50, 100, 100, 30);
        brightnessPanel.add(backButton);

        this.add(brightnessPanel);
    }

    public void chooseFileImage(){
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }
    }

    public void showOriginalImage(){
        try {
            JFrame tempFrame = new JFrame();
            JPanel tempPanel = new JPanel();

            BufferedImage image = ImageIO.read(file);
            ImageIcon icon = new ImageIcon(image.getScaledInstance(-1, 800, Image.SCALE_DEFAULT));
            JLabel label = new JLabel(icon);
            tempPanel.add(label);

            tempPanel.setSize(1800, 1000);
            tempFrame.setTitle("Image Viewer");
            tempFrame.setSize(1800, 1000);
            tempFrame.setVisible(true);
            tempFrame.setResizable(true);
            tempFrame.add(tempPanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void grayScaleImage(){
        try {
            JFrame tempFrame = new JFrame();
            JPanel tempPanel = new JPanel();

            BufferedImage image = ImageIO.read(file);
            ColorSpace colorSpace = ColorSpace.getInstance(ColorSpace.CS_GRAY);
            ColorConvertOp op = new ColorConvertOp(colorSpace, null);
            op.filter(image, image);
            ImageIcon icon = new ImageIcon(image.getScaledInstance(-1, 800, Image.SCALE_DEFAULT));
            JLabel label = new JLabel(icon);
            tempPanel.add(label);

            tempPanel.setSize(1800, 1000);
            tempFrame.setTitle("Image Viewer");
            tempFrame.setSize(1800, 1000);
            tempFrame.setVisible(true);
            tempFrame.setResizable(true);
            tempFrame.add(tempPanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showResizeImage(int width, int height){
        try {
            JFrame tempFrame = new JFrame();
            JPanel tempPanel = new JPanel();

            BufferedImage originalImage = ImageIO.read(file);
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            ImageIcon icon = new ImageIcon(resizedImage);
            JLabel label = new JLabel(icon);
            tempPanel.add(label);

            tempPanel.setSize(1800, 1000);
            tempFrame.setTitle("Image Viewer");
            tempFrame.setSize(1800, 1000);
            tempFrame.setVisible(true);
            tempFrame.setResizable(true);
            tempFrame.add(tempPanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showBrightnessImage(float brightness){
        try {
            JFrame tempFrame = new JFrame();
            JPanel tempPanel = new JPanel();

            BufferedImage originalImage = ImageIO.read(file);
            RescaleOp op = new RescaleOp(brightness, 0, null);
            BufferedImage newImage = op.filter(originalImage, null);
            ImageIcon icon = new ImageIcon(newImage.getScaledInstance(-1, 800, Image.SCALE_DEFAULT));
            JLabel label = new JLabel(icon);
            tempPanel.add(label);

            tempPanel.setSize(1800, 1000);
            tempFrame.setTitle("Image Viewer");
            tempFrame.setSize(1800, 1000);
            tempFrame.setVisible(true);
            tempFrame.setResizable(true);
            tempFrame.add(tempPanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==resizeButton){
            this.getContentPane().removeAll();
            resizePanel();
            this.revalidate();
            this.repaint();
        } else if(e.getSource()==showImageButton){
            showOriginalImage();
        } else if(e.getSource()==grayscaleButton){
            grayScaleImage();
        } else if(e.getSource()==showResizeButton){
            int width = Integer.parseInt(widthTextField.getText());
            int height = Integer.parseInt(heightTextField.getText());
            showResizeImage(width, height);
        } else if(e.getSource()==brightnessButton){
            this.getContentPane().removeAll();
            brightnessPanel();
            this.revalidate();
            this.repaint();
        } else if(e.getSource()==showBrightnessButton){
            float brightness = Float.parseFloat(brightnessTextField.getText());
            showBrightnessImage(brightness);
        } else if(e.getSource()==selectFileButton){
            chooseFileImage();
        } else if(e.getSource()==closeButton){
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else if(e.getSource()==backButton){
            getContentPane().removeAll();
            mainPanel();
            revalidate();
            repaint();
        }
    }
}
public class Main {
    public static void main(String[] args) {

        new ImageViewerGUI();
    }
}

