package de.fh_koeln.avs.imageprocessor;

import de.fh_koeln.avs.global.ImageData;
import de.fh_koeln.avs.global.ROI;
import de.fh_koeln.avs.global.converter.MatToBufferedImageConverter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public class ImageProcessor implements IImageProcessor {

    private final MatToBufferedImageConverter matToBufferedImageConverter;
    private Mat rawImage;
    private Map<Integer, ROI> rois;

    public ImageProcessor() {
        this.matToBufferedImageConverter = new MatToBufferedImageConverter();
    }

    @Override
    public void setRawImage(ImageData image) {
        this.rawImage = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        this.rawImage.put(0, 0, image.getData());
    }

    @Override
    public void drawROIsAutomatically(int threshold, int minLineSize, int lineGap, boolean horizontalLines) {
        Mat grayImage = new Mat(rawImage.rows(), rawImage.cols(), CvType.CV_8UC1);
        Mat cannyImage = new Mat(rawImage.rows(), rawImage.cols(), CvType.CV_8UC1);
        Mat lines = new Mat();

        Imgproc.cvtColor(rawImage, grayImage, Imgproc.COLOR_RGB2GRAY, 4);
        Imgproc.Canny(grayImage, cannyImage, 80, 100);
        Imgproc.HoughLinesP(cannyImage, lines, 1, Math.PI / 180, threshold, minLineSize, lineGap);

        double[][] filteredLines = filterLines(lines, horizontalLines);
        sortLines(filteredLines, horizontalLines);

        rois = createROIs(filteredLines);

        lines.release();
        cannyImage.release();
        grayImage.release();
    }

    private Map<Integer, ROI> createROIs(double[][] filteredLines) {
        Map<Integer, ROI> temp = new HashMap<>();
        int id = 0;
        for (int i = 0; i < filteredLines.length - 1; i++) {
            double[] line1 = filteredLines[i];
            double[] line2 = filteredLines[i + 1];

            if (line1[3] - 20 <= line2[3] && line1[3] + 20 >= line2[3]) {
                ROI roi = new ROI(id, rawImage.cols(), rawImage.rows());
                roi.setStartPoint((int) line1[0], (int) line1[3]);
                roi.setEndPoint((int) line2[2], (int) line2[1]);
                temp.put(id, roi);

                id++;
            }
        }
        return temp;
    }

    private double[][] filterLines(Mat lines, boolean horizontalLines) {
        List<double[]> temp = new ArrayList<>();
        for (int i = 0; i < lines.cols(); i++) {
            double[] vec = lines.get(0, i);
            if (horizontalLines) {
                if (vec[0] < vec[2] - 20 || vec[0] > vec[2] + 20) {
                    temp.add(vec);
                }
            } else {
                if (vec[1] < vec[3] - 20 || vec[1] > vec[3] + 20) {
                    temp.add(vec);
                }
            }
        }
        double[][] filteredLines = new double[temp.size()][4];
        for (int i = 0; i < temp.size(); i++) {
            filteredLines[i] = temp.get(i);
        }
        return filteredLines;
    }

    private void sortLines(double[][] lines, boolean horizontalLines) {
        Arrays.sort(lines, (final double[] entry1, final double[] entry2) -> {
            final double xEntry1 = entry1[0];
            final double yEntry1 = entry1[3];
            final double xEntry2 = entry2[0];
            final double yEntry2 = entry2[3];

            if (horizontalLines) {
                if (xEntry1 + 20 < xEntry2) {
                    return -1;
                } else if (xEntry1 - 20 <= xEntry2 && xEntry1 + 20 >= xEntry2) {
                    return yEntry1 < yEntry2 ? -1 : 1;
                } else {
                    return 1;
                }
            } else {
                if (yEntry1 + 20 < yEntry2) {
                    return -1;
                } else if (yEntry1 - 20 <= yEntry2 && yEntry1 + 20 >= yEntry2) {
                    return xEntry1 < xEntry2 ? -1 : 1;
                } else {
                    return 1;
                }
            }
        });
    }

    @Override
    public BufferedImage getImage() {
        return matToBufferedImageConverter.convertToBufferedImage(rawImage, true);
    }

    @Override
    public void processImage() {
        List<MatOfPoint> contours;
        int i = 0;
        for (ROI roi : rois.values()) {
            Mat imageChunk = new Mat(rawImage, new Rect(new Point(roi.getX(), roi.getY()), new Point(roi.getX() + roi.getWidth(), roi.getY() + roi.getHeight())));

            Mat imageHSV = new Mat(imageChunk.size(), Core.DEPTH_MASK_8U);
            Mat imageBlurr = new Mat(imageChunk.size(), Core.DEPTH_MASK_8U);
            Mat imageA = new Mat(imageChunk.size(), Core.DEPTH_MASK_ALL);
            Imgproc.cvtColor(imageChunk, imageHSV, Imgproc.COLOR_BGR2GRAY);
            Imgproc.GaussianBlur(imageHSV, imageBlurr, new Size(5, 5), 0);
            Imgproc.adaptiveThreshold(imageBlurr, imageA, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 7, 5);

            contours = new ArrayList<>();
            Imgproc.findContours(imageA, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
            System.out.println("Contours for " + i + ": " + contours.size());
            if (contours.size() > 10) {
                roi.setObjectDetected(true);
            } else {
                roi.setObjectDetected(false);
            }
            i++;
        }
    }

    @Override
    public Map<Integer, ROI> getROIs() {
        return rois;
    }

    @Override
    public void setROIs(Map<Integer, ROI> rois) {
        this.rois = rois;
    }
}
