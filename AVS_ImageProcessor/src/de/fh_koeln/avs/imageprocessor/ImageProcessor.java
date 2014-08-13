package de.fh_koeln.avs.imageprocessor;

import de.fh_koeln.avs.global.ImageChunkData;
import de.fh_koeln.avs.global.ImageData;
import de.fh_koeln.avs.global.converter.MatToBufferedImageConverter;
import java.awt.image.BufferedImage;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public class ImageProcessor implements IImageProcessor {

    private final MatToBufferedImageConverter matToBufferedImageConverter;
    private Mat rawImage;
    private Mat lines;
    private Mat imageWithLines;
    private ImageChunkData[] processedImageChunks;

    public ImageProcessor() {
        this.matToBufferedImageConverter = new MatToBufferedImageConverter();
    }

    @Override
    public void setRawImage(ImageData image) {
        this.rawImage = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        this.rawImage.put(0, 0, image.getData());
    }

    @Override
    public void drawLines(int threshold, int minLineSize, int lineGap) {
        // TODO 
        Mat thresholdImage = new Mat(rawImage.rows(), rawImage.cols(), CvType.CV_8UC1);
        imageWithLines = new Mat(rawImage.rows(), rawImage.cols(), CvType.CV_8UC3);
        byte[] temp = new byte[rawImage.rows() * rawImage.cols() * 3];
        rawImage.get(0, 0, temp);
        imageWithLines.put(0, 0, temp);
        Imgproc.cvtColor(rawImage, thresholdImage, Imgproc.COLOR_RGB2GRAY, 4);
        Imgproc.Canny(thresholdImage, thresholdImage, 80, 100);
        lines = new Mat();

        Imgproc.HoughLinesP(thresholdImage, lines, 1, Math.PI / 180, threshold, minLineSize, lineGap);

        for (int i = 0; i < lines.cols(); i++) {
            double[] vec = lines.get(0, i);
            double x1 = vec[0],
                    y1 = vec[1],
                    x2 = vec[2],
                    y2 = vec[3];
            Point start = new Point(x1, y1);
            Point end = new Point(x2, y2);

            Core.line(imageWithLines, start, end, new Scalar(255, 0, 0), 3);
        }
        thresholdImage.release();
        lines.release();
    }

    @Override
    public BufferedImage getImageWithLines() {
        return matToBufferedImageConverter.convertToBufferedImage(imageWithLines, true);
    }

    @Override
    public void processImage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImageChunkData[] getImageChunks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getProcessedImageChunksInformation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
