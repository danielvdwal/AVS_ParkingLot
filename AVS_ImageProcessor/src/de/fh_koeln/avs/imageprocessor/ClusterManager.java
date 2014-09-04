package de.fh_koeln.avs.imageprocessor;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import de.fh_koeln.avs.global.ImageData;
import de.fh_koeln.avs.global.ROI;
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
    private String id;

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
            id = imageMap.keySet().iterator().next();
        } catch (IllegalStateException ex) {
            return false;
        }
        return true;
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
        return imageMap.get(id);
    }

    @Override
    public void sendROIs(Map<Integer, ROI> rois) {
        System.out.printf("Send image to: imageprocessor_%s\n", id);
        IMap map = hz.getMap(String.format("imageprocessor_%s", id));
        map.putAll(rois);
    }
}
