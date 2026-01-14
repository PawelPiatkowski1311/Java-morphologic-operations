package image.Operations;

import image.Image;
import image.factories.KernelFactory;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class MultipleDilatationOperation {
    public Image dilateMultiple(Image image, int iterations) {
        Mat dilatedImageMat = new Mat();
        Mat kernel = KernelFactory.getKernel(Imgproc.MORPH_RECT, 3);

        for (int i = 0; i < iterations; i++) {
            Imgproc.dilate(image.getImage(), dilatedImageMat, kernel);
        }

        return new Image(dilatedImageMat);
    }
}
