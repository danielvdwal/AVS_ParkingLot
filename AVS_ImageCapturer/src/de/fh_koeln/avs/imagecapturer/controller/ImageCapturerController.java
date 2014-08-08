package de.fh_koeln.avs.imagecapturer.controller;

import de.fh_koeln.avs.imagecapturer.converter.MatToBufferedImageConverter;
import java.awt.image.BufferedImage;
import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

/**
 *
 * @author Daniel van der Wal
 */
public class ImageCapturerController implements IImageCapturerController {

    private final VideoCapture camera;
    private final MatToBufferedImageConverter matToBufferedImageConverter;
    private BufferedImage capturedBufferedImage;

    public ImageCapturerController() {
        camera = new VideoCapture();
        matToBufferedImageConverter = new MatToBufferedImageConverter();
    }

    @Override
    public void startCamera() {
        if (camera.open(0)) {
            System.out.println("Camera started");
        } else {
            System.out.println("Couldn't start camera");
        }
    }

    @Override
    public void stopCamera() {
        camera.release();
        if (!camera.isOpened()) {
            System.out.println("Camera stopped");
        } else {
            System.out.println("Couldn't stop camera");
        }
    }

    @Override
    public BufferedImage getCapturedImage() {
        if (camera.isOpened()) {
            Mat image = null;
            camera.read(image);
            capturedBufferedImage
                    = matToBufferedImageConverter.convertToBufferedImage(image);
        }
        return capturedBufferedImage;
    }
}
