package de.fh_koeln.avs.imageprocessor;

import de.fh_koeln.avs.global.ImageChunkData;
import de.fh_koeln.avs.global.ImageData;
import de.fh_koeln.avs.global.converter.MatToBufferedImageConverter;
import de.fh_koeln.avs.global.converter.MatToBufferedImageConverterGray;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public class ImageProcessor implements IImageProcessor {

    private final MatToBufferedImageConverter matToBufferedImageConverter;
    private final MatToBufferedImageConverterGray matToBufferedImageConverterGray;
    private Mat rawImage;
    private Mat cannyImage;
    private Mat lines;
    private Mat imageWithLines;
    private List<ImageChunkData> processedImageChunks;

    private Mat firstChunk;
    private Mat contourImage;

    public ImageProcessor() {
        this.matToBufferedImageConverter = new MatToBufferedImageConverter();
        this.matToBufferedImageConverterGray = new MatToBufferedImageConverterGray();
    }

    @Override
    public void setRawImage(ImageData image) {
        this.rawImage = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        this.rawImage.put(0, 0, image.getData());
    }

    @Override
    public void drawLines(int threshold, int minLineSize, int lineGap) {
        // TODO 
        if (cannyImage != null) {
            cannyImage.release();
        }
        Mat grayImage = new Mat(rawImage.rows(), rawImage.cols(), CvType.CV_8UC1);
        cannyImage = new Mat(rawImage.rows(), rawImage.cols(), CvType.CV_8UC1);
        imageWithLines = new Mat(rawImage.rows(), rawImage.cols(), CvType.CV_8UC3);
        byte[] temp = new byte[rawImage.rows() * rawImage.cols() * 3];
        rawImage.get(0, 0, temp);
        imageWithLines.put(0, 0, temp);
        Imgproc.cvtColor(rawImage, grayImage, Imgproc.COLOR_RGB2GRAY, 4);
        Imgproc.Canny(grayImage, cannyImage, 80, 100);
        lines = new Mat();

        Imgproc.HoughLinesP(cannyImage, lines, 1, Math.PI / 180, threshold, minLineSize, lineGap);

        double[][] filteredLines = filterLines();
        sortLines(filteredLines);

        processedImageChunks = createChunkData(filteredLines);

        // TODO kleine Mat in die ImageChunks
        for (int i = 0; i < filteredLines.length; i++) {
            double[] vec = filteredLines[i];
            double x1 = vec[0],
                    y1 = vec[1],
                    x2 = vec[2],
                    y2 = vec[3];
            Point start = new Point(x1, y1);
            Point end = new Point(x2, y2);
            Core.line(imageWithLines, start, end, new Scalar(0, 0, 255), 3);
            System.out.println("line " + i + ": x1 - " + x1 + " y1 - " + y1 + " x2 - " + x2 + " y2 - " + y2 + "\n");
        }

        processedImageChunks.stream().forEach((chunk) -> {
            Point start = new Point(chunk.getX1(), chunk.getY1());
            Point end = new Point(chunk.getX2(), chunk.getY2());
            Core.rectangle(imageWithLines, start, end, new Scalar(0, 255, 0), 3);
        });

        grayImage.release();
        lines.release();
    }

    private List<ImageChunkData> createChunkData(double[][] filteredLines) {
        List<ImageChunkData> chunkData = new ArrayList<>();
        for (int i = 0; i < filteredLines.length - 1; i++) {
            double[] line1 = filteredLines[i];
            double[] line2 = filteredLines[i + 1];

            if (line1[3] - 20 <= line2[3] && line1[3] + 20 >= line2[3]) {
                chunkData.add(new ImageChunkData(line1[0], line1[3], line2[2], line2[1]));
            }
        }
        return chunkData;
    }

    private double[][] filterLines() {
        List<double[]> temp = new ArrayList<>();
        for (int i = 0; i < lines.cols(); i++) {
            double[] vec = lines.get(0, i);
            if (vec[1] != vec[3]) {
                temp.add(vec);
            }
        }
        double[][] filteredLines = new double[temp.size()][4];
        for (int i = 0; i < temp.size(); i++) {
            filteredLines[i] = temp.get(i);
        }
        return filteredLines;
    }

    private void sortLines(double[][] lines) {
        Arrays.sort(lines, (final double[] entry1, final double[] entry2) -> {
            final double x1Entry1 = entry1[0];
            final double y2Entry1 = entry1[3];
            final double x1Entry2 = entry2[0];
            final double y2Entry2 = entry2[3];

            if (y2Entry1 + 20 < y2Entry2) {
                return -1;
            } else if (y2Entry1 - 20 <= y2Entry2 && y2Entry1 + 20 >= y2Entry2) {
                return x1Entry1 < x1Entry2 ? -1 : 1;
            } else {
                return 1;
            }

        });
    }

    @Override
    public BufferedImage getCannyImage() {
        return matToBufferedImageConverterGray.convertToBufferedImage(cannyImage, true);
    }

    @Override
    public BufferedImage getImageWithLines() {
        return matToBufferedImageConverter.convertToBufferedImage(imageWithLines, true);
    }

    @Override
    public void processImage() {
        List<MatOfPoint> contours;
        int i = 0;
        for (ImageChunkData chunkData : processedImageChunks) {
            chunkData.setRoi(rawImage);
            System.out.println("chunk " + i + ": ");
            Mat imageChunk = chunkData.getImageChunk();

            // TODO Remove
            if (i == 0) {
                firstChunk = chunkData.getImageChunk();
                contourImage = new Mat(firstChunk.rows(), firstChunk.cols(), CvType.CV_8UC3);
                contourImage.put(0, 0, chunkData.getData());
            }

            Mat imageHSV = new Mat(imageChunk.size(), Core.DEPTH_MASK_8U);
            Mat imageBlurr = new Mat(imageChunk.size(), Core.DEPTH_MASK_8U);
            Mat imageA = new Mat(imageChunk.size(), Core.DEPTH_MASK_ALL);
            Imgproc.cvtColor(imageChunk, imageHSV, Imgproc.COLOR_BGR2GRAY);
            Imgproc.GaussianBlur(imageHSV, imageBlurr, new Size(5, 5), 0);
            Imgproc.adaptiveThreshold(imageBlurr, imageA, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 7, 5);

            contours = new ArrayList<>();
            Imgproc.findContours(imageA, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
            for (MatOfPoint contour : contours) {
                System.out.println("ContourArea: " + Imgproc.contourArea(contour));

                Rect rect = Imgproc.boundingRect(contour);
                // TODO remove
                if (i == 0) {
                    //System.out.println(rect.x +","+rect.y+","+rect.height+","+rect.width);
                    Core.rectangle(contourImage, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255));
                }
            }
            System.out.println("-------------------------------");
            i++;
        }
    }

    @Override
    public BufferedImage getFirstChunkImage() {
        return matToBufferedImageConverter.convertToBufferedImage(firstChunk, true);
    }

    @Override
    public BufferedImage getContoursImage() {
        return matToBufferedImageConverter.convertToBufferedImage(contourImage, true);
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
