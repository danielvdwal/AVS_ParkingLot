package de.fh_koeln.avs.imageprocessor;

import de.fh_koeln.avs.global.ROI;
import java.awt.image.BufferedImage;
import java.util.Map;
import org.opencv.core.Core;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public class ImageProcessorController implements IImageProcessorController {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    private final IImageProcessor imageProcessor;
    private final IClusterManager clusterManager;

    public ImageProcessorController() {
        this.imageProcessor = new ImageProcessor();
        this.clusterManager = new ClusterManager();
    }

    @Override
    public boolean connectToCluster() {
        return clusterManager.connect();
    }

    @Override
    public boolean disconnectFromCluster() {
        return clusterManager.disconnect();
    }

    @Override
    public void getRawImage() {
        imageProcessor.setRawImage(clusterManager.getRawImage());
    }

    @Override
    public void drawLines(int threshold, int minLineSize, int lineGap) {
        imageProcessor.drawLines(threshold, minLineSize, lineGap);
    }

    @Override
    public BufferedImage getCannyImage() {
        return imageProcessor.getCannyImage();
    }

    @Override
    public BufferedImage getImageWithLines() {
        return imageProcessor.getImageWithLines();
    }

    @Override
    public void processImage() {
        imageProcessor.processImage();
        clusterManager.sendROIs(imageProcessor.getROIs());
    }

    @Override
    public String getProcessedImageChunksInformation() {
        return imageProcessor.getProcessedImageChunksInformation();
    }
}
