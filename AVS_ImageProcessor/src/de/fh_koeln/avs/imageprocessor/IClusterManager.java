package de.fh_koeln.avs.imageprocessor;

import de.fh_koeln.avs.global.ImageChunkData;
import de.fh_koeln.avs.global.ImageData;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public interface IClusterManager {
    
    boolean connect();
    boolean disconnect();
    ImageData getRawImage(int id);
    // ImageChunkData... == ImageChunkData[] ^^
    void sendImageChunks(int id, ImageChunkData... imageChunks);
}
