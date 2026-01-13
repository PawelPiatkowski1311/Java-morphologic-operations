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
        System.out.println("load <ścieżka>");
        System.out.println("erode");
        System.out.println("show");
        System.out.println("save <ścieżka>");
        System.out.println("exit");

        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine();
            String[] parts = line.split(" ");

            switch (parts[0]) {
                case "load":
                    imageService.loadImage(parts[1]);
                    break;

                case "erode":
                    imageService.erode();
                    break;

                case "show":
                    imageService.displayImage();
                    break;

                case "save":
                    imageService.saveImage(parts[1]);
                    break;

                case "exit":
                    return;

                default:
                    System.out.println("Nieznana komenda");
            }
        }
    }
}
