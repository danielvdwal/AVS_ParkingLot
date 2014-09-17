package de.fh_koeln.avs.dashboard;

import de.fh_koeln.avs.global.ImageData;
import de.fh_koeln.avs.global.ROI;
import de.fh_koeln.avs.global.converter.MatToBufferedImageConverter;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Map;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 *
 * @author Daniel van der Wal
 */
public class DashboardController implements IDashboardController {

    private final MatToBufferedImageConverter converter;
    private final IClusterManager clusterManager;

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public DashboardController() {
        this.clusterManager = new ClusterManager();
        this.converter = new MatToBufferedImageConverter();
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
    public BufferedImage getRawImage(String clientName) {
        ImageData data = clusterManager.getRawImage(clientName);
        Mat mat = new Mat(data.getHeight(), data.getWidth(), CvType.CV_8UC3);
        mat.put(0, 0, data.getData());
        return converter.convertToBufferedImage(mat, true);
    }

    @Override
    public Collection<String> getImageCapturerNames() {
        return clusterManager.getConnectedImageCapturerNames();
    }

    @Override
    public Map<Integer, ROI> getROIs(String clientName) {
        return clusterManager.getROIs(clientName);
    }
}
