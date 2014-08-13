package de.fh_koeln.avs.clustermanager;

import de.fh_koeln.avs.global.ImageChunkData;
import de.fh_koeln.avs.global.ImageData;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public class ClusterManagerController implements IClusterManagerController {

    private IClusterManager clusterManger;
    private IRawImageManager rawImageManager;
    private IProcessedImageManager processedImageManager;
    
    @Override
    public boolean createCluster() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean destroyCluster() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
