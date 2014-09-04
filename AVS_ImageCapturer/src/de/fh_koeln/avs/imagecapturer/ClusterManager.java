package de.fh_koeln.avs.imagecapturer;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import de.fh_koeln.avs.global.ImageData;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public class ClusterManager implements IClusterManager {

    private ClientNetworkConfig networkConfig;
    private ClientConfig clientConfig;
    private HazelcastInstance hz;
    private IMap<Integer, ImageData> imageMap;

    @Override
    public boolean connect() {
        try {
            networkConfig = new ClientNetworkConfig();
            // TODO hazelcast config file
            networkConfig.addAddress("192.168.178.32:5701");
            clientConfig = new ClientConfig();
            clientConfig.setNetworkConfig(networkConfig);
            hz = HazelcastClient.newHazelcastClient(clientConfig);
            imageMap = hz.getMap("capturedImages");
            return true;
        } catch (IllegalStateException ex) {
            return false;
        }
    }

    @Override
    public boolean disconnect() {
        if (hz != null) {
            try {
                hz.shutdown();
                return true;
            } catch (IllegalStateException ex) {
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
        System.out.println("picture send: " + System.currentTimeMillis());
        imageMap.put(1, image);
    }
}
