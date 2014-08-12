package de.fh_koeln.avs.imagecapturer;

import java.awt.image.BufferedImage;
import org.opencv.core.Core;

/**
 *
 * @author Daniel van der Wal
 */
public class ImageCapturerController implements IImageCapturerController {


    /*private VideoCapture camera;
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

    @Override
    public Mat getRawCapturedImage() {
        Mat image = null;
        if (camera.isOpened()) {
            image = new Mat();
            camera.read(image);
        }
        return image; 
    }*/

    @Override
    public boolean openCapture(int camId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean closeCapture() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BufferedImage nextFrame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean connectToCluster() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean disconnectFromCluster() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
