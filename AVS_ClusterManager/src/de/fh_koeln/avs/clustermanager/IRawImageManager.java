package de.fh_koeln.avs.clustermanager;

import de.fh_koeln.avs.global.ImageData;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public interface IRawImageManager {
    
    void createRawImageCollection(String id);
    void destroyRawImageCollection(String id);
    ImageData getNextRawImage(String id);
    void putNewRawImage(String id, ImageData image);
}
