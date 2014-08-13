package de.fh_koeln.avs.imageprocessor;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.DistributedObject;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import de.fh_koeln.avs.global.ImageChunkData;
import de.fh_koeln.avs.global.ImageData;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public class ClusterManager implements IClusterManager {

    private String imageCapturerQueueId;
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
            for (DistributedObject distObj : hz.getDistributedObjects()) {
                if (distObj instanceof IQueue && distObj.getName().startsWith("imagecapturer_")) {
                    imageCapturerQueueId = distObj.getName();
                    break;
                }
            }
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
    public ImageData getRawImage() {
        try {
            IQueue<ImageData> queue = hz.getQueue(imageCapturerQueueId);
            return queue.take();
        } catch (InterruptedException ex) {
            Logger.getLogger(ClusterManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void sendImageChunks(int id, ImageChunkData... imageChunks) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
