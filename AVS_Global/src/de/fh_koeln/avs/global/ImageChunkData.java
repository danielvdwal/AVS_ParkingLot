package de.fh_koeln.avs.global;

import java.io.Serializable;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public final class ImageChunkData implements Serializable {

    private final int id;
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    private final int width;
    private final int height;
    private transient Mat roi;
    private byte[] data;
    private boolean objectDetected;

    public ImageChunkData(int id, double x1, double y1, double x2, double y2) {
        this(id, x1, y1, x2, y2, (int) (x2 - x1), (int) (y2 - y1));
    }

    public ImageChunkData(int id, double x1, double y1, double x2, double y2, Mat image) {
        this(id, x1, y1, x2, y2, (int) (x2 - x1), (int) (y2 - y1));
        setRoi(image);
    }

    private ImageChunkData(int id, double x1, double y1, double x2, double y2, int width, int height) {
        this.id = id;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.width = width;
        this.height = height;
        this.data = new byte[this.width * this.height * 3];
    }

    public int getId() {
        return id;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public byte[] getData() {
        byte[] temp = new byte[data.length];
        System.arraycopy(data, 0, temp, 0, data.length);
        return temp;
    }

    public Mat getImageChunk() {
        return roi;
    }

    public void setRoi(Mat image) {
        roi = new Mat(image, new Rect(new Point(x1, y1), new Point(x2, y2)));
        roi.get(0, 0, data);
    }

    public boolean isObjectDetected() {
        return objectDetected;
    }

    public void setObjectDetected(boolean objectDetected) {
        this.objectDetected = objectDetected;
    }
}
