package de.fh_koeln.avs.imageprocessor;

import de.fh_koeln.avs.global.ImageChunkData;
import de.fh_koeln.avs.global.ImageData;
import java.util.Map;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public interface IClusterManager {

    boolean connect();

    boolean disconnect();

    ImageData getRawImage();

    void sendImageChunks(Map<Integer, ImageChunkData> imageChunks);
}
