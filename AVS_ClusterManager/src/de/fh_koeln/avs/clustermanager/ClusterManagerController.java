package de.fh_koeln.avs.clustermanager;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public class ClusterManagerController implements IClusterManagerController {

    private final IClusterManager clusterManager;

    public ClusterManagerController() {
        this.clusterManager = new ClusterManager();
    }

    @Override
    public boolean createCluster() {
        return clusterManager.createCluster();
    }

    @Override
    public boolean destroyCluster() {
        return clusterManager.destroyCluster();
    }
}
