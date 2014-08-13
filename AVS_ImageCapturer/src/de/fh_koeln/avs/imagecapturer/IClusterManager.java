package de.fh_koeln.avs.imagecapturer;

import de.fh_koeln.avs.global.ImageData;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public interface IClusterManager {
    
    boolean connect();
    boolean disconnect();
    boolean isConnected();
    void sendRawImage(ImageData image);
}
