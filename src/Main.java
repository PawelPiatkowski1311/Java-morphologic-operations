import org.opencv.core.Core;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        // Ładowanie OpenCV (najpewniejsza metoda)
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        ImageService service = new ImageService();
        Protocol protocol = new Protocol(service);

        protocol.start();
    }
}
