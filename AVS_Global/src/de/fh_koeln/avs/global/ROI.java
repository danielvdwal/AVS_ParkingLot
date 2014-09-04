package de.fh_koeln.avs.global;

import java.io.Serializable;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public final class ROI implements Serializable {

    private final int id;
    private int x, y;
    private int width, height;
    private boolean objectDetected;

    public ROI(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = 0;
        this.height = 0;
    }

    public void calculateValues(int newX, int newY) {
        x = x < newX ? x : newX;
        y = y < newY ? y : newY;
        width = x < newX ? newX - x : x - newX;
        height = y < newY ? newY - y : y - newY;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isObjectDetected() {
        return objectDetected;
    }

    public void setObjectDetected(boolean objectDetected) {
        this.objectDetected = objectDetected;
    }
}
