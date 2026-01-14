package image;

import java.io.IOException;
import java.util.Scanner;

public class Protocol {

    private final ImageService imageService;
    private final Scanner scanner = new Scanner(System.in);

    public Protocol(ImageService imageService) {
        this.imageService = imageService;
    }

    public void start() throws IOException {
        System.out.println("Dostępne komendy:");
        System.out.println("erode");
        System.out.println("multiple_erode <n>");
        System.out.println("dilate");
        System.out.println("multiple_dilate <n>");
        System.out.println("morpho_open_close");
        System.out.println("contour_extract");
        System.out.println("skeletonize");
        System.out.println("exit");

        while (true) {
            System.out.print(">> ");
            String line = scanner.nextLine();
            String[] parts = line.split(" ");

            switch (parts[0]) {
                case "erode":
                    if (imageService.erode()) System.out.println("Wykonano erozję");
                    break;

                case "multiple_erode":
                    if (imageService.multipleErode(Integer.parseInt(parts[1]))) System.out.println("Wykonano erozję");
                    break;

                case "dilate":
                    if (imageService.dilate()) System.out.println("Wykonano dylatacje");
                    break;

                case "multiple_dilate":
                    if (imageService.multipleDilate(Integer.parseInt(parts[1]))) System.out.println("Wykonano dylatacje");
                    break;

                case "morpho_open_close":
                    if (imageService.morphologicalOpenAndClose()) System.out.println("Wykonano morfologiczne otwarcie i zamknięcie");
                    break;

                case "contour_extract":
                    if (imageService.contourExtract()) System.out.println("Wykonano ekstrakcję konturów");
                    break;

                case "skeletonize":
                    if (imageService.skeletonization()) System.out.println("Wykonano szkieletyzację");
                    break;

                case "exit":
                    return;

                default:
                    System.out.println("Nieznana komenda");
            }
        }
    }
}
