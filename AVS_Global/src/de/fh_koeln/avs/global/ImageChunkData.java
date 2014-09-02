package de.fh_koeln.avs.global;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public class ImageChunkData {
    
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    
    public ImageChunkData(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
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
}
