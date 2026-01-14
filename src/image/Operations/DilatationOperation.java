package image.Operations;

import image.Image;
import image.factories.KernelFactory;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class DilatationOperation {
    public Image dilate(Image image) {
        Mat dilatedImageMat = new Mat();

        Mat kernel = KernelFactory.getKernel(Imgproc.MORPH_RECT, 3);

        Imgproc.dilate(image.getImage(), dilatedImageMat, kernel);

        return new Image(dilatedImageMat);
    }
}
