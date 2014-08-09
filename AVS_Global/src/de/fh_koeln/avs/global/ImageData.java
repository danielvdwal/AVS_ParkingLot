package de.fh_koeln.avs.global;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.Serializable;

/**
 *
 * @author Daniel van der Wal
 */
public class ImageData implements Serializable {
    
    private final byte[] data;
    private final int width;
    private final int height;
    private final int imageType;
    
    public ImageData(BufferedImage image) {
        this.data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.imageType = image.getType();
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

    public int getImageType() {
        return imageType;
    }
}
