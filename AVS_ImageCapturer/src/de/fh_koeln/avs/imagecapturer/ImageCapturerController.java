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
    private Mat currentFrame;
    private ImageData imageData;

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
        currentFrame = imageCapture.nextFrame();
        return matToBufferedImageConverter.convertToBufferedImage(currentFrame, true);
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
    public void sendRawImage() {
        if (clusterManager.isConnected()) {
            if(imageData == null) {
                imageData = new ImageData(currentFrame.cols(), currentFrame.rows());
            }
            currentFrame.get(0, 0, imageData.getData());
            clusterManager.sendRawImage(imageData);
        }
    }
}
