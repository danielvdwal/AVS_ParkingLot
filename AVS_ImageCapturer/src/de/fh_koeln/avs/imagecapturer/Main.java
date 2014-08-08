package de.fh_koeln.avs.imagecapturer;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

/**
 *
 * @author Daniel van der Wal
 */
public class Main {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        VideoCapture camera = new VideoCapture(0);
        if (camera.isOpened()) {
            System.out.println("Camera started");
        } else {
            System.out.println("Camera Error");
            System.exit(-1);
        }

        int i = 0;
        while(camera.isOpened() && i < 100) {
            Mat frame = new Mat();
            camera.read(frame);
            i++;
        }
        
        camera.release();
    }
}
