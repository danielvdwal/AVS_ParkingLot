package de.fh_koeln.avs.imagecapturer.controller;

import java.awt.image.BufferedImage;

/**
 *
 * @author Daniel van der Wal
 */
public interface IImageCapturerController {
    
    void startCamera();
    void stopCamera();
    BufferedImage getCapturedImage(boolean multiThreaded);
}
