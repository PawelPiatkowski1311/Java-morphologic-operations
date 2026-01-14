package image.Operations;

import image.Image;
import image.factories.KernelFactory;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class ContourExtractionOperation {
    // Metoda 1: kontur = dylatacja - obraz oryginalny
    public Image contourDilateMinusOriginal(Image image) {

        Mat imageMat = image.getImage();
        Mat dilatedImageMat = new Mat();

        // Element strukturalny
        Mat kernel = KernelFactory.getKernel(Imgproc.MORPH_RECT, 3);

        // Dylatacja obrazu
        Imgproc.dilate(imageMat, dilatedImageMat, kernel);

        // Obraz wynikowy – różnica
        Mat extractedImageMat = new Mat();
        Core.subtract(dilatedImageMat, imageMat, extractedImageMat);

        return new Image(extractedImageMat);
    }

    // Metoda 2: kontur = obraz oryginalny - erozja
    public Image contourOriginalMinusErode(Image image) {

        Mat imageMat = image.getImage();
        Mat eroded = new Mat();

        // Element strukturalny
        Mat kernel = KernelFactory.getKernel(Imgproc.MORPH_RECT, 3);

        // Erozja obrazu
        Imgproc.erode(imageMat, eroded, kernel);

        // Obraz wynikowy – różnica
        Mat subtractedImageMat = new Mat();
        Core.subtract(imageMat, eroded, subtractedImageMat);

        return new Image(subtractedImageMat);
    }

    // Metoda 3: kontur = dylatacja - erozja
    public Image contourDilateMinusErode(Image image) {
        Mat imageMat = image.getImage();
        Mat dilated = new Mat();
        Mat erodedImageMat = new Mat();

        // Element strukturalny
        Mat kernel = KernelFactory.getKernel(Imgproc.MORPH_RECT, 3);

        // Dylatacja i erozja
        Imgproc.dilate(imageMat, dilated, kernel);
        Imgproc.erode(imageMat, erodedImageMat, kernel);

        // Różnica dylatacji i erozji
        Mat subtractedImageMat = new Mat();
        Core.subtract(dilated, erodedImageMat, subtractedImageMat);

        return new Image(subtractedImageMat);
    }
}
