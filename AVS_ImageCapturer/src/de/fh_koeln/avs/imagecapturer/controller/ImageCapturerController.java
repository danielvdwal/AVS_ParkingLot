package de.fh_koeln.avs.imagecapturer.controller;

import de.fh_koeln.avs.global.converter.MatToBufferedImageConverter;
import java.awt.image.BufferedImage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
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
            camera.set(Highgui.CV_CAP_PROP_FRAME_WIDTH, 640);
            camera.set(Highgui.CV_CAP_PROP_FRAME_HEIGHT, 480);
        }
        if (camera.isOpened()) {
            System.out.printf("Camera is running with %sx%s\n", camera.get(Highgui.CV_CAP_PROP_FRAME_WIDTH), camera.get(Highgui.CV_CAP_PROP_FRAME_HEIGHT));
        } else {
            System.out.println("Couldn't start camera");
        }
    }

    @Override
    public void stopCamera() {
        camera = null;
        if (camera == null) {
            System.out.println("Camera stopped");
        } else {
            System.out.println("Couldn't stop camera");
        }
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

    //@Override
    public Mat getRawCapturedImage() {
        Mat image = null;
        if (camera.isOpened()) {
            image = new Mat();
            camera.read(image);
        }
        return image; 
    }
}
