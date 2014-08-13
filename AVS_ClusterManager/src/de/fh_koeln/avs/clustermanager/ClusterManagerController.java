package de.fh_koeln.avs.clustermanager;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public class ClusterManagerController implements IClusterManagerController {

    private final Config config;
    private HazelcastInstance hz;

    public ClusterManagerController() {
        this.config = new Config();
    }

    @Override
    public boolean createCluster() {
        if (hz == null) {
            hz = Hazelcast.newHazelcastInstance(config);
        }
        return true;

    }

    @Override
    public boolean destroyCluster() {
        if (hz != null) {
            hz.shutdown();
            hz = null;
        }
        return true;
    }
}
