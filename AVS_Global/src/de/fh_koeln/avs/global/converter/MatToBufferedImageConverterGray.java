package de.fh_koeln.avs.global.converter;

import java.awt.image.BufferedImage;
import org.opencv.core.Mat;

/**
 *
 * @author Daniel van der Wal
 */
public final class MatToBufferedImageConverterGray {

    private BufferedImage img;
    private byte[] data;

    public BufferedImage convertToBufferedImage(Mat matImage, boolean multiThreaded) {
        getSpace(matImage);
        matImage.get(0, 0, data);
        img.getRaster().setDataElements(0, 0, matImage.cols(), matImage.rows(),
                data);

        return img;
    }

    private void getSpace(Mat imageMat) {
        int w = imageMat.cols();
        int h = imageMat.rows();
        if (data == null || data.length != w * h) {
            data = new byte[w * h];
        }
        if (img == null || img.getWidth() != w || img.getHeight() != h
                || img.getType() != BufferedImage.TYPE_BYTE_GRAY) {
            img = new BufferedImage(w, h,
                    BufferedImage.TYPE_BYTE_GRAY);
        }
    }
}
