package de.fh_koeln.avs.dashboard;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import de.fh_koeln.avs.global.ImageData;
import de.fh_koeln.avs.global.ROI;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public class ClusterManager implements IClusterManager {

    private ClientNetworkConfig networkConfig;
    private ClientConfig clientConfig;
    private HazelcastInstance hz;
    private IMap<String, ImageData> imageMap;

    @Override
    public boolean connect() {
        try {
            networkConfig = new ClientNetworkConfig();
            // TODO hazelcast config file
            networkConfig.addAddress("139.6.65.27:5701");
            clientConfig = new ClientConfig();
            clientConfig.setNetworkConfig(networkConfig);
            hz = HazelcastClient.newHazelcastClient(clientConfig);
            imageMap = hz.getMap("capturedImages");
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
    public ImageData getRawImage(String clientName) {
        return imageMap.get(clientName);
    }

    @Override
    public Collection<String> getConnectedImageCapturerNames() {
        return imageMap.keySet();
    }

    @Override
    public Map<Integer, ROI> getROIs(String clientName) {
        String id = String.format("imageprocessor_%s", clientName);
        return hz.getMap(id);
    }
}
