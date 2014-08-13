package de.fh_koeln.avs.clustermanager;

import de.fh_koeln.avs.global.ImageChunkData;
import java.util.concurrent.ConcurrentMap;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public class ProcessedImageManager implements IProcessedImageManager {

    private ConcurrentMap<String, ImageChunkData[]> processedImageChunksCollections;
    
    @Override
    public void createProcessedImageChunksCollection(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroyProcessedImageChunksCollection(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImageChunkData[] getProcessedImageChunks(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putProcessedImageChunks(String id, ImageChunkData[] imageChunks) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
