package de.fh_koeln.avs.global;

import java.io.Serializable;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public final class ROI implements Serializable {

    private final int id;
    private int x1, y1;
    private int x2, y2;
    private boolean objectDetected;

    public ROI(int id) {
        this.id = id;
        this.x1 = 0;
        this.y1 = 0;
        this.x2 = 0;
        this.y2 = 0;
    }
    
    public void setStartPoint(int x, int y) {
        this.x1 = x;
        this.y1 = y;
    }
    
    public void setEndPoint(int x, int y) {
        this.x2 = x;
        this.y2 = y;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x1 < x2 ? x1 : x2;
    }

    public int getY() {
        return y1 < y2 ? y1 : y2;
    }

    public int getWidth() {
        return x1 < x2 ? x2 - x1 : x1 - x2;
    }

    public int getHeight() {
        return y1 < y2 ? y2 - y1 : y1 - y2;
    }

    public boolean isObjectDetected() {
        return objectDetected;
    }

    public void setObjectDetected(boolean objectDetected) {
        this.objectDetected = objectDetected;
    }
}
