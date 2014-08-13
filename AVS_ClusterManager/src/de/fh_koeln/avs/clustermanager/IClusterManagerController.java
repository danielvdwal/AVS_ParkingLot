package de.fh_koeln.avs.clustermanager;

import de.fh_koeln.avs.global.ImageChunkData;
import de.fh_koeln.avs.global.ImageData;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public interface IClusterManagerController {
    
    boolean createCluster();
    boolean destroyCluster();
    void createRawImageCollection(String id);
    void destroyRawImageCollection(String id);
    ImageData getNextRawImage(String id);
    void putNewRawImage(String id, ImageData image);
    void createProcessedImageChunksCollection(String id);
    void destroyProcessedImageChunksCollection(String id);
    ImageChunkData[] getProcessedImageChunks(String id);
    void putProcessedImageChunks(String id, ImageChunkData[] imageChunks);
}
