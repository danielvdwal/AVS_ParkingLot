package de.fh_koeln.avs.imagecapturer;

import java.awt.image.BufferedImage;

/**
 *
 * @author Daniel van der Wal
 */
public interface IImageCapturerController {

    boolean openCapture(int camId);

    boolean closeCapture();

    BufferedImage nextFrame();

    boolean connectToCluster();

    boolean disconnectFromCluster();
}
