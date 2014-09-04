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
        return imageMap.get(1);
    }

    @Override
    public void sendROIs(Map<Integer, ROI> rois) {
        System.out.printf("Send image to: imageprocessor_%s\n", hz.getName());
        IMap map = hz.getMap(String.format("imageprocessor_%s", hz.getName()));
        map.putAll(rois);
    }
}
