package de.fh_koeln.avs.imagecapturer.controller;

import de.fh_koeln.avs.imagecapturer.converter.MatToBufferedImageConverter;
import java.awt.image.BufferedImage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

/**
 *
 * @author Daniel van der Wal
 */
public class ImageCapturerController implements IImageCapturerController {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    private VideoCapture camera;
    private final MatToBufferedImageConverter matToBufferedImageConverter;
    private BufferedImage capturedBufferedImage;

    public ImageCapturerController() {
        matToBufferedImageConverter = new MatToBufferedImageConverter();
    }

    @Override
    public void startCamera() {
        if (camera == null) {
            camera = new VideoCapture(0);
        }
        if (camera.isOpened()) {
            System.out.println("Camera is running");
        } else {
            System.out.println("Couldn't start camera");
        }
    }

    @Override
    public void stopCamera() {
        camera = null;
    }

    @Override
    public BufferedImage getCapturedImage(boolean multiThreaded) {
        if (camera.isOpened()) {
            Mat image = new Mat();
            camera.read(image);
            capturedBufferedImage
                    = matToBufferedImageConverter.convertToBufferedImage(image, multiThreaded);
        }
        return capturedBufferedImage;
    }
}
