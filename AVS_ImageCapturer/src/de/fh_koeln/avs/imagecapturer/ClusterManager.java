package de.fh_koeln.avs.imagecapturer;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastException;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import de.fh_koeln.avs.global.ImageData;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public class ClusterManager implements IClusterManager {

    private ClientNetworkConfig networkConfig;
    private ClientConfig clientConfig;
    private HazelcastInstance hz;

    @Override
    public boolean connect() {
        try {
            networkConfig = new ClientNetworkConfig();
            // TODO hazelcast config file
            networkConfig.addAddress("139.6.65.26:5701");
            clientConfig = new ClientConfig();
            clientConfig.setNetworkConfig(networkConfig);
            hz = HazelcastClient.newHazelcastClient(clientConfig);
            return true;
        } catch (IllegalStateException hzex) {
            return false;
        }
    }

    @Override
    public boolean disconnect() {
        if (hz != null) {
            try {
                hz.shutdown();
                return true;
            } catch (IllegalStateException hzex) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public boolean isConnected() {
        return hz != null;
    }

    @Override
    public void sendRawImage(ImageData image) {
        IQueue queue = hz.getQueue(hz.getName());
        queue.offer(image);
    }
}
