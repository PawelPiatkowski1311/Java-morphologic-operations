package image.Operations;

import image.Image;
import image.factories.KernelFactory;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class MultipleErosionOperation {
    public Image erodeMultiple(Image image, int iterations) {

        Mat imageMat = image.getImage().clone();

        Mat kernel = KernelFactory.getKernel(Imgproc.MORPH_RECT, 3);

        for (int i = 0; i < iterations; i++) {
            Imgproc.erode(imageMat, imageMat, kernel);
        }

        return new Image(imageMat);
    }
}
