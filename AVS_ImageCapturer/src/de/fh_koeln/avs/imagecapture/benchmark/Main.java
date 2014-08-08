package de.fh_koeln.avs.imagecapture.benchmark;


import de.fh_koeln.avs.imagecapturer.controller.IImageCapturerController;
import de.fh_koeln.avs.imagecapturer.controller.ImageCapturerController;
import de.fh_koeln.avs.imagecapturer.converter.MatToBufferedImageConverter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dvanderw
 */
public class Main {

    public static void main(String[] args) {
        final ImageCapturerController imgCapCon = new ImageCapturerController();
        final int nFrames = 100;
        imgCapCon.startCamera();
        System.out.printf("Started single threaded camera capture of %d frames\n", nFrames);
        long startTime = System.nanoTime();
        for (int i = 0; i < nFrames; i++) {
            //imgCapCon.getCapturedImage();
        }
        long endTime = System.nanoTime();
        System.out.printf("Stopped single threaded camera capture of %d frames\n", nFrames);
        long duration = (endTime - startTime);
        System.out.printf("Capturing %d took: %d ns - %f µs - %f ms - %f s\n", nFrames, duration, duration/1000.0f, duration/1000.0f/1000.0f, duration/1000.0f/1000.0f/1000.0f);
        
        System.out.printf("Started multi threaded camera capture of %d frames\n", nFrames);
        startTime = System.nanoTime();
        for (int i = 0; i < nFrames; i++) {
            imgCapCon.getCapturedImageMT();
        }
        endTime = System.nanoTime();
        System.out.printf("Stopped multi threaded camera capture of %d frames\n", nFrames);
        duration = (endTime - startTime);
        System.out.printf("Capturing %d took: %d ns - %f µs - %f ms - %f s\n", nFrames, duration, duration/1000.0f, duration/1000.0f/1000.0f, duration/1000.0f/1000.0f/1000.0f);
    }
}
