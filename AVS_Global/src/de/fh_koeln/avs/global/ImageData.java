package de.fh_koeln.avs.global;

import java.io.Serializable;

/**
 *
 * @author Daniel van der Wal
 */
public class ImageData implements Serializable {

    private final byte[] data;
    private final int width;
    private final int height;

    public ImageData(int width, int height) {
        this.width = width;
        this.height = height;
        this.data = new byte[width * height * 3];
    }
    
    public byte[] getData() {
        return data;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
