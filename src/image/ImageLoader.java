package image;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;

import static org.opencv.imgproc.Imgproc.resize;

public class ImageLoader {
    Image image;

    public Image loadImage(String path, int method) {
        // Utworzenie nowego obiektu image
        image = new Image(Imgcodecs.imread(path, method));

        // Przypisanie do zmiennej macierzy zdjęcia
        Mat imageMat = image.getImage();

        // Uzyskanie wymiarów zdjęcia
        int originalWidth = imageMat.cols();
        int originalHeight = imageMat.rows();

        // Przygotowanie zmiennych do przeskalowania zdjęcia tak, aby mieściło się w oknie z uwzględnieniem
        // współczynnika proporcji
        int newHeight = 780;
        double aspectRatio = (double) newHeight / originalHeight;
        int newWidth = (int) (originalWidth * aspectRatio);

        // Przeskalowanie zdjęcia
        resize(imageMat, imageMat, new Size(newWidth, newHeight));

        // Ustawienie przeskalowanego zdjęcia w objekcie image
        image.setImage(imageMat);

        // Zwrócenie instancji
        return image;
    }
}
