package de.fh_koeln.avs.imagecapturer.benchmark;

import de.fh_koeln.avs.imagecapturer.ImageCapturerController;

/**
 *
 * @author Daniel van der Wal
 */
public class Main {

    public static void main(String[] args) {
        /*final ImageCapturerController imgCapCon = new ImageCapturerController();
        final int nFrames = 100;
        imgCapCon.startCamera();
        System.out.printf("Started single threaded camera capture of %d frames\n", nFrames);
        long totalStartTime = System.nanoTime();
        for (int i = 0; i < nFrames; i++) {
            long startTime = System.nanoTime();
            imgCapCon.getCapturedImage(false);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.printf("Capturing one frame took: %d ns - %f µs - %f ms - %f s\n", duration, duration / 1000.0f, duration / 1000.0f / 1000.0f, duration / 1000.0f / 1000.0f / 1000.0f);
        }
        long totalEndTime = System.nanoTime();
        System.out.printf("Stopped single threaded camera capture of %d frames\n", nFrames);
        long totalDuration = (totalEndTime - totalStartTime);
        System.out.printf("Capturing %d frames took: %d ns - %f µs - %f ms - %f s\n", nFrames,
                totalDuration, totalDuration / 1000.0f, totalDuration / 1000.0f / 1000.0f, totalDuration / 1000.0f / 1000.0f / 1000.0f);

        System.out.printf("Started multi threaded camera capture of %d frames\n", nFrames);
        System.out.println("Checking split length!");
        totalStartTime = System.nanoTime();
        for (int i = 0; i < nFrames; i++) {
            long startTime = System.nanoTime();
            imgCapCon.getCapturedImage(true);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.printf("Capturing one frame took: %d ns - %f µs - %f ms - %f s\n", duration, duration / 1000.0f, duration / 1000.0f / 1000.0f, duration / 1000.0f / 1000.0f / 1000.0f);
        }
        totalEndTime = System.nanoTime();
        System.out.printf("Stopped multi threaded camera capture of %d frames\n", nFrames);
        totalDuration = (totalEndTime - totalStartTime);
        System.out.printf("Capturing %d frames took: %d ns - %f µs - %f ms - %f s\n", nFrames,
                totalDuration, totalDuration / 1000.0f, totalDuration / 1000.0f / 1000.0f, totalDuration / 1000.0f / 1000.0f / 1000.0f);
                */
    }
}
