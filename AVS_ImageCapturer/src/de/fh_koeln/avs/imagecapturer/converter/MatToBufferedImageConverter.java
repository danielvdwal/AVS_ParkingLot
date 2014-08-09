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

    private final static ForkJoinPool pool = new ForkJoinPool();

    private BufferedImage img;
    private byte[] data;

    public BufferedImage convertToBufferedImage(Mat matImage, boolean multiThreaded) {
        getSpace(matImage);
        matImage.get(0, 0, data);
        img.getRaster().setDataElements(0, 0, matImage.cols(), matImage.rows(),
                data);

        byte[] rgbData = ((DataBufferByte) img.getRaster().
                getDataBuffer()).getData();

        if (multiThreaded) {
            switchFromBGRToRBG(rgbData);
        } else {
            switchFromBGRToRBGSingleThreaded(rgbData);
        }

        return img;
    }

    private void switchFromBGRToRBG(byte[] rgbData) {
        BGRToRGBSwitcher switcher = new BGRToRGBSwitcher(0, data.length, data, rgbData);
        pool.invoke(switcher);
    }

    private void switchFromBGRToRBGSingleThreaded(byte[] rgbData) {
        System.arraycopy(data, 0, rgbData, 0, data.length);
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

        private static final int SPLIT_LENGTH = 350_000; // Due to best results in benchmarking
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
            if (length > SPLIT_LENGTH) {
                int midLength = length / 2;
                invokeAll(new BGRToRGBSwitcher(i, midLength, data, rgbData),
                        new BGRToRGBSwitcher(i + midLength, midLength, data, rgbData));
            } else {
                computeDirectly();
            }
        }
    }
}
