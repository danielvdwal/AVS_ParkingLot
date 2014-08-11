package de.fh_koeln.avs.global.converter;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import static org.opencv.core.CvType.CV_8UC3;
import org.opencv.core.Mat;

/**
 *
 * @author Daniel van der Wal
 */
public class BufferedImageToMatConverter {

    private Mat mat;

    public Mat convertToMat(BufferedImage image) {
        byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();

        mat = new Mat(image.getHeight(), image.getWidth(), CV_8UC3);
        mat.put(0, 0, pixels);

        return mat;
    }
}
