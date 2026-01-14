package image.Operations;

import image.Image;
import image.factories.KernelFactory;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class SkeletonizationOperation {

    // Ścienianie (szkieletyzacja) obrazu binarnego
    public Image skeletonize(Image image) {

        // Klon obrazu wejściowego (nie niszczymy oryginału)
        Mat img = image.getImage().clone();

        // Binaryzacja
        Imgproc.threshold(img, img, 128, 255, Imgproc.THRESH_BINARY);

        Core.bitwise_not(img, img);

        // Obraz wynikowy – szkielet (początkowo czarny)
        Mat skeleton = Mat.zeros(img.size(), img.type());

        // Element strukturalny (krzyżowy – najlepszy do szkieletu)
        Mat kernel = KernelFactory.getKernel(Imgproc.MORPH_CROSS, 3);

        Mat eroded = new Mat();
        Mat dilated = new Mat();
        Mat temp = new Mat();

        boolean finished;

        do {
            // Erozja obrazu
            Imgproc.erode(img, eroded, kernel);

            // Dylatacja obrazu po erozji
            Imgproc.dilate(eroded, dilated, kernel);

            // Różnica: oryginał - dylatacja
            Core.subtract(img, dilated, temp);

            // Dodanie różnicy do szkieletu
            Core.bitwise_or(skeleton, temp, skeleton);

            // Przygotowanie obrazu do kolejnej iteracji
            eroded.copyTo(img);

            // Sprawdzenie, czy obraz całkowicie zanikł
            finished = (Core.countNonZero(img) == 0);

        } while (!finished);

        return new Image(skeleton);
    }
}
