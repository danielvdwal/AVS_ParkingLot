package de.fh_koeln.avs.clustermanager;

import de.fh_koeln.avs.global.ImageChunkData;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public interface IProcessedImageManager {
    
    void createProcessedImageChunksCollection(int id);
    void destroyProcessedImageChunksCollection(int id);
    ImageChunkData[] getProcessedImageChunks(int id);
    void putProcessedImageChunks(int id, ImageChunkData[] imageChunks);
}
