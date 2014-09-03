package de.fh_koeln.avs.imageprocessor;

import java.awt.image.BufferedImage;
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
    public BufferedImage getFirstChunkImage() {
        return imageProcessor.getFirstChunkImage();
    }

    @Override
    public BufferedImage getContoursImage() {
        return imageProcessor.getContoursImage();
    }

    @Override
    public BufferedImage getImageWithLines() {
        return imageProcessor.getImageWithLines();
    }

    @Override
    public void processImage() {
        imageProcessor.processImage();
    }

    @Override
    public String getProcessedImageChunksInformation() {
        return imageProcessor.getProcessedImageChunksInformation();
    }
}
