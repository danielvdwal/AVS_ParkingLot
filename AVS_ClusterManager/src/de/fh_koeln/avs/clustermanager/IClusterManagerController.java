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
    void createRawImageCollection(int id);
    void destroyRawImageCollection(int id);
    ImageData getNextRawImage(int id);
    void putNewRawImage(int id, ImageData image);
    void createProcessedImageChunksCollection(int id);
    void destroyProcessedImageChunksCollection(int id);
    ImageChunkData[] getProcessedImageChunks(int id);
    void putProcessedImageChunks(int id, ImageChunkData[] imageChunks);
}
