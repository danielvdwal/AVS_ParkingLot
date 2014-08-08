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

        BGRToRGBSwitcher con = new BGRToRGBSwitcher(img, 0, matImage.cols() * matImage.rows() * 3 - 1, data, rgbData);

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

        private final BufferedImage img;
        private final int i;
        private final int j;
        private final byte[] data;
        private final byte[] rgbData;

        public BGRToRGBSwitcher(BufferedImage img, int i, int j, byte[] data, byte[] rgbData) {
            this.img = img;
            this.i = i;
            this.j = j;
            this.data = data;
            this.rgbData = rgbData;
        }

        private void computeDirectly() {
            rgbData[i] = data[j];
            rgbData[j] = data[i];
        }

        @Override
        protected void compute() {
            if (i + 1000000 >= j) {
                computeDirectly();
                return;
            }
            invokeAll(new BGRToRGBSwitcher(img, i, j, data, rgbData),
                    new BGRToRGBSwitcher(img, i + 1, j - 1, data, rgbData));
        }
    }
}
