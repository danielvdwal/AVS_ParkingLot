package de.fh_koeln.avs.imagecapturer;

import java.awt.image.BufferedImage;

/**
 *
 * @author Daniel van der Wal
 */
public interface IImageCapturerController {

    void setCamId(int camId);
    
    boolean openCapture();

    boolean closeCapture();

    BufferedImage nextFrame();

    boolean connectToCluster();

    boolean disconnectFromCluster();
    
    void sendRawImage();
}
