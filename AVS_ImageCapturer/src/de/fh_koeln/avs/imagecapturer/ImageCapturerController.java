package de.fh_koeln.avs.imagecapturer;

import de.fh_koeln.avs.global.ImageData;
import de.fh_koeln.avs.global.converter.MatToBufferedImageConverter;
import java.awt.image.BufferedImage;
import org.opencv.core.Mat;

/**
 *
 * @author Daniel van der Wal
 */
public class ImageCapturerController implements IImageCapturerController {

    private final IImageCapture imageCapture;
    private final IClusterManager clusterManager;
    private final MatToBufferedImageConverter matToBufferedImageConverter;

    public ImageCapturerController() {
        this.imageCapture = new CameraImageCapture();
        //this.imageCapture = new FileImageCapture();
        this.clusterManager = new ClusterManager();
        this.matToBufferedImageConverter = new MatToBufferedImageConverter();
    }

    @Override
    public boolean openCapture(int camId) {
        return imageCapture.open(camId);
    }

    @Override
    public boolean closeCapture() {
        return imageCapture.close();
    }

    @Override
    public BufferedImage nextFrame() {
        Mat frame = imageCapture.nextFrame();
        if (clusterManager.isConnected()) {
            ImageData data = new ImageData(frame);
            clusterManager.sendRawImage(data);
        }
        return matToBufferedImageConverter.convertToBufferedImage(frame, true);
    }

    @Override
    public boolean connectToCluster() {
        return clusterManager.connect();
    }

    @Override
    public boolean disconnectFromCluster() {
        return clusterManager.disconnect();
    }
}
