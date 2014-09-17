package de.fh_koeln.avs.imageprocessor;

import de.fh_koeln.avs.global.ROI;
import java.awt.image.BufferedImage;
import java.util.Collection;
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
    private final ROIFileReader roiFileReader;
    private final ROIFileWriter roiFileWriter;
    private String currentImageCapturerId;

    public ImageProcessorController() {
        this.imageProcessor = new ImageProcessor();
        this.clusterManager = new ClusterManager();
        this.roiFileReader = new ROIFileReader();
        this.roiFileWriter = new ROIFileWriter();
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
    public void pullRawImage() {
        imageProcessor.setRawImage(clusterManager.getRawImage());
    }

    @Override
    public void drawROIsAutomatically(int threshold, int minLineSize, int lineGap, boolean horizontal) {
        imageProcessor.drawROIsAutomatically(threshold, minLineSize, lineGap, horizontal);
    }
    
    @Override
    public Map<Integer, ROI> getROIs() {
        return imageProcessor.getROIs();
    }

    @Override
    public void setROIs(Map<Integer, ROI> rois) {
        imageProcessor.setROIs(rois);
        roiFileWriter.writeROIFile(currentImageCapturerId, rois);
    }

    @Override
    public BufferedImage getImage() {
        return imageProcessor.getImage();
    }

    @Override
    public void processImage() {
        imageProcessor.processImage();
        clusterManager.sendROIs(imageProcessor.getROIs());
    }

    @Override
    public void setSelectedImageCapturerName(String name) {
        clusterManager.setId(name);
        currentImageCapturerId = name;
        if(roiFileReader.roiFileExists(name)) {
            imageProcessor.setROIs(roiFileReader.readROIFile(name));
        } else {
            imageProcessor.setROIs(null);
        }
    }

    @Override
    public Collection<String> getImageCapturerNames() {
        return clusterManager.getConnectedImageCapturerNames();
    }
}
