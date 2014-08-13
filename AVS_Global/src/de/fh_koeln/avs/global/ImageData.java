package de.fh_koeln.avs.global;

import java.io.Serializable;
import org.opencv.core.Mat;

/**
 *
 * @author Daniel van der Wal
 */
public class ImageData implements Serializable {
    
    private final byte[] data;
    private final int width;
    private final int height;
    
    public ImageData(Mat image) {
        this(image.width(), image.height(), new byte[image.width() * image.height() * 3]);
        image.get(0, 0, this.data);
    }
    
    private ImageData(int width, int height, byte[] data) {
        this.width = width;
        this.height = height;
        this.data = data;
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
