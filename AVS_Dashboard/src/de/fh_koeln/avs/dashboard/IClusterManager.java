package de.fh_koeln.avs.dashboard;

import de.fh_koeln.avs.global.ImageData;
import java.util.Map;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public interface IClusterManager {

    boolean connect();

    boolean disconnect();

    ImageData getRawImage();

}
