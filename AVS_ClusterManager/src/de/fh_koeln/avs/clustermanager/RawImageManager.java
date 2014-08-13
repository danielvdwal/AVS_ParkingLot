package de.fh_koeln.avs.clustermanager;

import de.fh_koeln.avs.global.ImageData;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public class RawImageManager implements IRawImageManager {

    private ConcurrentMap<String, BlockingQueue<ImageData>> rawImageCollections;
    
    @Override
    public void createRawImageCollection(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroyRawImageCollection(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImageData getNextRawImage(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putNewRawImage(String id, ImageData image) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
