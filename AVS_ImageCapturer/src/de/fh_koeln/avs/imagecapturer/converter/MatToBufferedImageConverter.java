package de.fh_koeln.avs.imagecapturer.converter;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import org.opencv.core.Mat;

/**
 *
 * @author Daniel van der Wal
 */
public final class MatToBufferedImageConverter {

    private BufferedImage img;
    private byte[] data;
    private byte[] rgbData;

    public MatToBufferedImageConverter() {
    }

    public BufferedImage convertToBufferedImageST(Mat matImage) {
        getSpace(matImage);
        matImage.get(0, 0, data);
        img.getRaster().setDataElements(0, 0, matImage.cols(), matImage.rows(),
                data);

        switchFromBGRToRBGST();

        return img;
    }

    public BufferedImage convertToBufferedImage(Mat matImage) {
        getSpace(matImage);
        matImage.get(0, 0, data);
        img.getRaster().setDataElements(0, 0, matImage.cols(), matImage.rows(),
                data);

        switchFromBGRToRBG(matImage);

        return img;
    }

    private void switchFromBGRToRBGST() {
        rgbData = ((DataBufferByte) img.getRaster().
                getDataBuffer()).getData();
        System.arraycopy(data, 0, rgbData, 0, data.length);
    }

    private void switchFromBGRToRBG(Mat matImage) {
        rgbData = ((DataBufferByte) img.getRaster().
                getDataBuffer()).getData();

        BGRToRGBSwitcher con = new BGRToRGBSwitcher(0, data.length, data, rgbData);

        ForkJoinPool pool = new ForkJoinPool();

        pool.invoke(con);
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

    private class BGRToRGBSwitcher extends RecursiveAction {

        private final int splitLength = 350_000;
        private final int i;
        private final int length;
        private final byte[] data;
        private final byte[] rgbData;

        public BGRToRGBSwitcher(int i, int length, byte[] data, byte[] rgbData) {
            this.i = i;
            this.length = length;
            this.data = data;
            this.rgbData = rgbData;
        }

        private void computeDirectly() {
            System.arraycopy(data, i, rgbData, i, length);
        }

        @Override
        protected void compute() {
            if (length > splitLength) {
                int midLength = length / 2;
                invokeAll(new BGRToRGBSwitcher(i, midLength, data, rgbData),
                        new BGRToRGBSwitcher(i + midLength, midLength, data, rgbData));
            }
            else {
                computeDirectly();
            }
        }
    }
}
