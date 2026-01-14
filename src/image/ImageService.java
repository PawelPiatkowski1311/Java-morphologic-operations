package image;

import image.Operations.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageService {
    private Image image;
    private final String sourcePath;
    private static final String IMAGES_FOLDER = "images";


    ImageService() throws IOException {
        this.sourcePath = Paths.get("images/source images/lena.jpg").toString();
        Path mainFolder = Paths.get("images/");
        Files.createDirectories(mainFolder);
    }

    private final ImageLoader loader = new ImageLoader();
    private final ImageWriter writer = new ImageWriter();

    private final ErosionOperation erosionOperation = new ErosionOperation();
    private final MultipleErosionOperation multipleErosionOperation = new MultipleErosionOperation();
    private final DilatationOperation dilatationOperation = new DilatationOperation();
    private final MultipleDilatationOperation multipleDilatationOperation = new MultipleDilatationOperation();
    private final MorphologicalOpenAndCloseOperation morphologicalOpenAndCloseOperation = new MorphologicalOpenAndCloseOperation();
    private final ContourExtractionOperation contourExtractionOperation = new ContourExtractionOperation();
    private final SkeletonizationOperation skeletonizationOperation = new SkeletonizationOperation();

    public boolean erode() throws IOException {
        image = loader.loadImage(sourcePath, Imgcodecs.IMREAD_GRAYSCALE);

        Image binarized = erosionOperation.binary(image);
        Image eroded3x3 = erosionOperation.erode(image, Imgproc.MORPH_RECT, 3);
        Image eroded5x5Rectangle = erosionOperation.erode(image, Imgproc.MORPH_RECT, 5);
        Image eroded5x5Cross = erosionOperation.erode(image, Imgproc.MORPH_CROSS, 5);

        String destinationFolder = getDestinationFolderPath("/eroded images");

        writer.writeImage(binarized, destinationFolder + "/binarized.jpg");
        writer.writeImage(eroded3x3, destinationFolder + "/eroded 3x3.jpg");
        writer.writeImage(eroded5x5Rectangle, destinationFolder + "/eroded 5x5 rectangle.jpg");
        writer.writeImage(eroded5x5Cross, destinationFolder + "/eroded 5x5 cross.jpg");

        return true;
    }

    public boolean multipleErode(int iterations) throws IOException {
        image = loader.loadImage(sourcePath, Imgcodecs.IMREAD_COLOR);

        Image multipleEroded = multipleErosionOperation.erodeMultiple(image, iterations);

        String destinationFolder = getDestinationFolderPath("/multiple eroded images");

        writer.writeImage(multipleEroded, destinationFolder + "/multiple eroded.jpg");

        return true;
    }

    public boolean dilate() throws IOException {
        image = loader.loadImage(sourcePath, Imgcodecs.IMREAD_COLOR);

        Image dilated = dilatationOperation.dilate(image);

        String destinationFolder = getDestinationFolderPath("/dilated images");

        writer.writeImage(dilated, destinationFolder + "/dilated.jpg");

        return true;
    }

    public boolean multipleDilate(int iterations) throws IOException {
        image = loader.loadImage(sourcePath, Imgcodecs.IMREAD_COLOR);

        Image multipleDilated = multipleDilatationOperation.dilateMultiple(image, iterations);

        String destinationFolder = getDestinationFolderPath("/multiple dilated images");


        writer.writeImage(multipleDilated, destinationFolder + "/multiple dilated.jpg");

        return true;
    }

    public boolean morphologicalOpenAndClose() throws IOException {
        image = loader.loadImage(sourcePath, Imgcodecs.IMREAD_COLOR);

        Image opened = morphologicalOpenAndCloseOperation.open(image);
        Image closed = morphologicalOpenAndCloseOperation.close(image);

        String destinationFolder = getDestinationFolderPath("/morphological opened and closed images");

        writer.writeImage(opened, destinationFolder + "/opened.jpg");
        writer.writeImage(closed, destinationFolder + "/closed.jpg");

        return true;
    }

    public boolean contourExtract() throws IOException {
        image = loader.loadImage(sourcePath, Imgcodecs.IMREAD_COLOR);

        Image dilateMinusOriginal = contourExtractionOperation.contourDilateMinusOriginal(image);
        Image originalMinusErode = contourExtractionOperation.contourOriginalMinusErode(image);
        Image dilateMinusErode = contourExtractionOperation.contourDilateMinusErode(image);

        String destinationFolder = getDestinationFolderPath("/extracted images");

        writer.writeImage(dilateMinusOriginal, destinationFolder + "/dilate minus original.jpg");
        writer.writeImage(originalMinusErode, destinationFolder + "/original minus erode.jpg");
        writer.writeImage(dilateMinusErode, destinationFolder + "/dilate minus erode.jpg");

        return true;
    }

    public boolean skeletonization() throws  IOException {
        String sourcePath = Paths.get("images/source images/p letter.jpg").toString();

        image = loader.loadImage(sourcePath, Imgcodecs.IMREAD_GRAYSCALE);

        Image skeletonized = skeletonizationOperation.skeletonize(image);

        String destinationFolder = getDestinationFolderPath("/skeletonized images");

        writer.writeImage(skeletonized, destinationFolder + "/skeleton.jpg");

        return true;
    }

    private String getDestinationFolderPath(String folderName) throws IOException {
        String destinationFolder = IMAGES_FOLDER + folderName;
        Files.createDirectories(Paths.get(destinationFolder));

        return destinationFolder;
    }
}