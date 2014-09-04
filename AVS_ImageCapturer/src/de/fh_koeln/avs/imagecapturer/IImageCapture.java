package de.fh_koeln.avs.imagecapturer;

import org.opencv.core.Mat;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public interface IImageCapture {

    int setCamId(int camId);
    
    boolean open();

    Mat nextFrame();

    boolean close();
}
