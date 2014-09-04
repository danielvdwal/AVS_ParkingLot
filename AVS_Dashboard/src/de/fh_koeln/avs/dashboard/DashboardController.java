package de.fh_koeln.avs.dashboard;

import de.fh_koeln.avs.global.ImageData;
import de.fh_koeln.avs.global.converter.MatToBufferedImageConverter;
import java.awt.image.BufferedImage;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 *
 * @author Daniel van der Wal
 */
public class DashboardController {
    
    private final MatToBufferedImageConverter converter;
    
    private final IClusterManager clusterManager;
    
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }


    public DashboardController() {
        this.clusterManager = new ClusterManager();
        this.converter = new MatToBufferedImageConverter();
    }
    
    public boolean connectToCluster() {
        return clusterManager.connect();
    }

    public boolean disconnectFromCluster() {
        return clusterManager.disconnect();
    }

    public BufferedImage getRawImage() {
        ImageData data = clusterManager.getRawImage();
        Mat mat = new Mat(data.getHeight(), data.getWidth(), CvType.CV_8UC3);
        mat.put(0, 0, data.getData());
        return converter.convertToBufferedImage(mat, true);
    }
}
