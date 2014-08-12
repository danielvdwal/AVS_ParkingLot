package de.fh_koeln.avs.clustermanager;

import de.fh_koeln.avs.global.ImageChunkData;
import java.util.concurrent.ConcurrentMap;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public class ProcessedImageManager implements IProcessedImageManager {

    private ConcurrentMap<Integer, ImageChunkData[]> processedImageChunksCollections;
    
    @Override
    public void createProcessedImageChunksCollection(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroyProcessedImageChunksCollection(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImageChunkData[] getProcessedImageChunks(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putProcessedImageChunks(int id, ImageChunkData[] imageChunks) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
