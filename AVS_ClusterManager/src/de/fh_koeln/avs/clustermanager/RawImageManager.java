package de.fh_koeln.avs.clustermanager;

import de.fh_koeln.avs.global.ImageData;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public class RawImageManager implements IRawImageManager {

    private ConcurrentMap<Integer, BlockingQueue<ImageData>> rawImageCollections;
    
    @Override
    public void createRawImageCollection(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroyRawImageCollection(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImageData getNextRawImage(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putNewRawImage(int id, ImageData image) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
