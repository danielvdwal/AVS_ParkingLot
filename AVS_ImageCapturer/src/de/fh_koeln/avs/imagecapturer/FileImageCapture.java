package de.fh_koeln.avs.imagecapturer;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public class FileImageCapture implements IImageCapture {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    private final Mat[] images;
    private Mat currentFrame;
    private int camId = 0;
    private int imageId = 0;

    public FileImageCapture() {
        images = new Mat[5];
        images[0] = Highgui.imread("parking_lot_0.jpg", Highgui.CV_LOAD_IMAGE_COLOR);
        images[1] = Highgui.imread("parking_lot_2.jpg", Highgui.CV_LOAD_IMAGE_COLOR);
        images[2] = Highgui.imread("parkingLot_0.jpg", Highgui.CV_LOAD_IMAGE_COLOR);
        images[3] = Highgui.imread("parkingLot_2.jpg", Highgui.CV_LOAD_IMAGE_COLOR);
        images[4] = Highgui.imread("parkingLot_4.jpg", Highgui.CV_LOAD_IMAGE_COLOR);
    }

    @Override
    public int setCamId(int camId) {
        if (camId == 0 || camId == 1) {
            this.camId = 2;
        } else {
            this.camId = 3;
        }
        imageId = camId;
        return this.camId;
    }

    @Override
    public boolean open() {
        if (imageId >= images.length) {
            return false;
        }
        currentFrame = images[imageId];
        return true;
    }

    @Override
    public Mat nextFrame() {
        return currentFrame;
    }

    @Override
    public boolean close() {
        currentFrame = null;
        return true;
    }
}
