package de.fh_koeln.avs.imageprocessor;

import de.fh_koeln.avs.global.ImageData;
import de.fh_koeln.avs.global.ROI;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public interface IImageProcessor {

    void setRawImage(ImageData image);

    void drawROIsAutomatically(int threshold, int minLineSize, int lineGap, boolean horizontalLines);

    BufferedImage getImage();

    void processImage();

    Map<Integer, ROI> getROIs();
    
    void setROIs(Map<Integer, ROI> rois);
}
