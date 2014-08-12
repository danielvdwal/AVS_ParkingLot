package de.fh_koeln.avs.clustermanager;

import de.fh_koeln.avs.global.ImageData;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public interface IRawImageManager {
    
    void createRawImageCollection(int id);
    void destroyRawImageCollection(int id);
    ImageData getNextRawImage(int id);
    void putNewRawImage(int id, ImageData image);
}
