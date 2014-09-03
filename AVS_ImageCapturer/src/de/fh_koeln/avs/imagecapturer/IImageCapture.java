package de.fh_koeln.avs.imagecapturer;

import org.opencv.core.Mat;

/**
 *
 * @author Daniel van der Wal, Nadim Khan
 */
public interface IImageCapture {

    boolean open(int camId);

    Mat nextFrame();

    boolean close();
}
