package image.Operations;

import image.Image;
import image.factories.KernelFactory;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class MorphologicalOpenAndCloseOperation {
    // Operacja otwarcia: erozja + dylatacja
    public Image open(Image image) {

        // Klonowanie obrazu wejściowego, aby nie modyfikować oryginału
        Mat openedImageMat = new Mat();

        Mat kernel = KernelFactory.getKernel(Imgproc.MORPH_RECT, 3);

        // Najpierw erozja – usunięcie drobnych obiektów
        Imgproc.erode(image.getImage(), openedImageMat, kernel);

        // Następnie dylatacja – przywrócenie kształtu większych obiektów
        Imgproc.dilate(openedImageMat, openedImageMat, kernel);

        return new Image(openedImageMat);
    }

    // Operacja domknięcia: dylatacja + erozja
    public Image close(Image image) {

        // Klonowanie obrazu wejściowego
        Mat closedImageMat = new Mat();

        // Element strukturalny
        Mat kernel = KernelFactory.getKernel(Imgproc.MORPH_RECT, 3);

        // Najpierw dylatacja – wypełnienie przerw i dziur
        Imgproc.dilate(image.getImage(), closedImageMat, kernel);

        // Następnie erozja – przywrócenie rozmiaru obiektów
        Imgproc.erode(closedImageMat, closedImageMat, kernel);

        return new Image(closedImageMat);
    }
}
