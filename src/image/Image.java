package image;

import org.opencv.core.Mat;

public class Image {
    Mat image;

    public Image(Mat image) {
        this.image = image;
    }

    public void setImage(Mat image) {
        this.image = image;
    }

    public Mat getImage() {
        return this.image;
    }
}
