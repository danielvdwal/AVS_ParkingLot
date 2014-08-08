package de.fh_koeln.avs.imagecapturer.controller;

import java.awt.image.BufferedImage;
import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

/**
 *
 * @author Daniel van der Wal
 */
public class ImageCapturerController implements IImageCapturerController {

    private VideoCapture camera;
    private Mat capturedImage;

    public ImageCapturerController() {
    
    }
    
    @Override
    public void startCamera() {
        if (camera == null) {
            camera = new VideoCapture(0);
        }
    }
    
    @Override
    public void stopCamera() {
        if(camera != null) {
            camera.release();
        }
    }
    
    @Override
    public BufferedImage getCapturedImage() {
        return null;
    }

}
