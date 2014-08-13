package de.fh_koeln.avs.clustermanager;

import de.fh_koeln.avs.global.ImageChunkData;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public interface IProcessedImageManager {
    
    void createProcessedImageChunksCollection(String id);
    void destroyProcessedImageChunksCollection(String id);
    ImageChunkData[] getProcessedImageChunks(String id);
    void putProcessedImageChunks(String id, ImageChunkData[] imageChunks);
}
