package image.Operations;

import image.Image;
import image.factories.KernelFactory;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;


public class ErosionOperation {
    public Image binary(Image image) {
        Mat binarized = new Mat();
        Imgproc.threshold(image.getImage(), binarized,128, 255, Imgproc.THRESH_BINARY);
        return new Image(binarized);
    }

    public Image erode(Image image, int method, int size) {
        Mat erodedImageMat = new Mat();
        Mat kernel = KernelFactory.getKernel(method, size);

        Imgproc.erode(image.getImage(), erodedImageMat, kernel);
        return new Image(erodedImageMat);
    }
}
