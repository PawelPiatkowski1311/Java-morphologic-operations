package image.factories;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public final class KernelFactory {
    private KernelFactory() {}

    public static Mat getKernel(int method, int size) {
        return Imgproc.getStructuringElement(
                method,
                new Size(size, size)
        );
    }
}
