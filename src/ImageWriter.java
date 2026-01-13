import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class ImageWriter {
    public void writeImage(Image image, String destinationPath) {
        Mat imageMat = image.getImage();

        // Zapisanie pliku w określonej ścieżce
        Imgcodecs.imwrite(destinationPath, imageMat);
    }

    public void writeImageMat(Mat imageMat, String destinationPath) {
        Imgcodecs.imwrite(destinationPath, imageMat);
    }
}
