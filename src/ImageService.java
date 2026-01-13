import org.opencv.imgcodecs.Imgcodecs;

import java.io.IOException;

public class ImageService {
    private Image image;

    private final ImageLoader loader = new ImageLoader();
    private final ImageProcessor processor = new ImageProcessor();
    private final ImageWriter writer = new ImageWriter();
    private final ImageDisplayer displayer = new ImageDisplayer();

    String sourcePath = "C:/Users/pawel/ONEDRI~1/Studia/PRZETW~1/LAB4~1/image.jpg";
    String destinationPath = "C:/Users/pawel/ONEDRI~1/Studia/PRZETW~1/LAB4~1/ERODED~1/";

    public void loadImage(String path) {
        image = loader.loadImage(sourcePath, Imgcodecs.IMREAD_COLOR);
        System.out.println("Obraz wczytany");
    }

    public void erode() throws IOException {
        image = loader.loadImage(sourcePath, Imgcodecs.IMREAD_GRAYSCALE);
        Image binary = processor.erode(image);
        writer.writeImage(binary, destinationPath + "binary");
        System.out.println("Wykonano erozję");
    }

    public void displayImage() throws IOException {
        displayer.displayImage(image);
    }

    public void saveImage(String path) {
        writer.writeImage(image, path);
    }
}