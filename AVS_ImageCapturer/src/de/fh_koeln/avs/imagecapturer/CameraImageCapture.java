package de.fh_koeln.avs.imagecapturer;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public class CameraImageCapture implements IImageCapture {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    private VideoCapture camera;
    private Mat capturedFrame;

    @Override
    public boolean open(int camId) {
        if (camera == null) {
            camera = new VideoCapture(camId);
            camera.set(Highgui.CV_CAP_PROP_FRAME_WIDTH, 640);
            camera.set(Highgui.CV_CAP_PROP_FRAME_HEIGHT, 480);
        }
        if (camera.isOpened()) {
            System.out.printf("Camera is running with %sx%s\n", camera.get(Highgui.CV_CAP_PROP_FRAME_WIDTH), camera.get(Highgui.CV_CAP_PROP_FRAME_HEIGHT));
            return true;
        } else {
            camera = null;
            System.out.println("Couldn't start camera");
            return false;
        }
    }

    @Override
    public Mat nextFrame() {
        if (camera.isOpened()) {
            capturedFrame = new Mat();
            camera.read(capturedFrame);
        }
        return capturedFrame;
    }

    @Override
    public boolean close() {
        camera = null;
        if (camera == null) {
            System.out.println("Camera stopped");
            return true;
        } else {
            System.out.println("Couldn't stop camera");
            return false;
        }
    }
}
