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
        if(camera == null) {
            camera = new VideoCapture(0);
        } else {
            camera.open(0);
        }
        if (camera.isOpened()) {
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
            Mat image = new Mat();
            camera.read(image);
            capturedBufferedImage
                    = matToBufferedImageConverter.convertToBufferedImage(image);
        }
        return capturedBufferedImage;
    }
}
