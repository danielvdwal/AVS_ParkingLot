package de.fh_koeln.avs.imageprocessor;

import de.fh_koeln.avs.global.ImageChunkData;
import de.fh_koeln.avs.global.ImageData;
import java.awt.image.BufferedImage;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public interface IImageProcessor {
    
    void setRawImage(ImageData image);
    void drawLines(int threshold, int minLineSize, int lineGap);
    BufferedImage getCannyImage();
    BufferedImage getImageWithLines();
    void processImage();
    ImageChunkData[] getImageChunks();
    String getProcessedImageChunksInformation();
}
