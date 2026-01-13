import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageDisplayer {
    public void displayImage(Image image) throws IOException {
        Mat imageMat = image.getImage();

        // Inicjalizacja obiektu matOfByte
        MatOfByte matOfByte = new MatOfByte();

        // Konwersja zdjęcia z mat na matOfByte
        Imgcodecs.imencode(".jpg", imageMat, matOfByte);

        // Konwersja z matOfByte na byteArray
        byte[] byteArray = matOfByte.toArray();

        // Inicjalizacja obiektu inputStream
        InputStream in = new ByteArrayInputStream(byteArray);

        // Przekazanie obiektu inputStream do obiektu bufImage metodą read
        BufferedImage bufImage = ImageIO.read(in); // wymaga wyjątku

        // Inicjalizacja ramki
        JFrame frame = new JFrame();

        // Ustawia zawartość ramki
        frame.getContentPane().add(new JLabel(new ImageIcon(bufImage)));
        frame.pack();

        // Włącza widoczność ramki
        frame.setVisible(true);
    }
}