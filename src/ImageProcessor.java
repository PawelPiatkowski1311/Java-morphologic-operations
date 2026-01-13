import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class ImageProcessor {
    public Image erode(Image input) {
        Mat result = new Mat();

        Mat kernel = Imgproc.getStructuringElement(
                Imgproc.MORPH_RECT,
                new Size(3, 3)
        );

        Imgproc.erode(input.getImage(), result, kernel);

        return new Image(result);
    }
}
