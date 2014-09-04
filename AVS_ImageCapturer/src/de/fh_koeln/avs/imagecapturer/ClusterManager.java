package de.fh_koeln.avs.imagecapturer;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import de.fh_koeln.avs.global.ImageData;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private int camId;

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
            id = String.format("%s_%d", InetAddress.getLocalHost().getHostName(), camId);
        } catch (IllegalStateException ex) {
            return false;
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClusterManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
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
        System.out.println("picture send to: " + id);
        imageMap.put(id, image);
    }

    @Override
    public void setCamId(int camId) {
        this.camId = camId;
    }
}
