package de.fh_koeln.avs.imageprocessor;

import java.awt.image.BufferedImage;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public interface IImageProcessorController {
    
    boolean connectToCluster();
    boolean disconnectFromCluster();
    void getRawImage();
    void drawLines();
    BufferedImage getImageWithLines();
    void processImage();
    String getProcessedImageChunksInformation();
}
