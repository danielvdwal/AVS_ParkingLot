package de.fh_koeln.avs.imagecapturer.converter;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import org.opencv.core.Mat;

/**
 *
 * @author Daniel van der Wal
 */
public final class MatToBufferedImageConverter {

    private BufferedImage img;
    private byte[] data;

    public BufferedImage convertToBufferedImage(Mat matImage) {
        getSpace(matImage);
        matImage.get(0, 0, data);
        img.getRaster().setDataElements(0, 0, matImage.cols(), matImage.rows(),
                data);
        
        return img;
    }
    

    private void getSpace(Mat imageMat) {
        int w = imageMat.cols();
        int h = imageMat.rows();
        if (data == null || data.length != w * h * 3) {
            data = new byte[w * h * 3];
        }
        if (img == null || img.getWidth() != w || img.getHeight() != h
                || img.getType() != BufferedImage.TYPE_3BYTE_BGR) {
            img = new BufferedImage(w, h,
                    BufferedImage.TYPE_3BYTE_BGR);
        }
    }
    /*
    int type = BufferedImage.TYPE_BYTE_GRAY;
        if (matImage.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize
                = matImage.channels() * matImage.cols() * matImage.rows();
        byte[] b = new byte[bufferSize];
        matImage.get(0, 0, b); // get all the pixels
        BufferedImage image = new BufferedImage(matImage.cols(),
                matImage.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().
                getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        return image;
    */
}
