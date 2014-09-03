package de.fh_koeln.avs.imageprocessor;

import de.fh_koeln.avs.global.ImageChunkData;
import de.fh_koeln.avs.global.ImageData;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public interface IImageProcessor {

    void setRawImage(ImageData image);

    void drawLines(int threshold, int minLineSize, int lineGap);

    BufferedImage getCannyImage();

    BufferedImage getFirstChunkImage();

    BufferedImage getContoursImage();

    BufferedImage getImageWithLines();

    void processImage();

    Map<Integer, ImageChunkData> getImageChunks();

    String getProcessedImageChunksInformation();
}
