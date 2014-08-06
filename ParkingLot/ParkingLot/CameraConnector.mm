//
//  CameraConnector.m
//  ParkingLot
//
//  Created by Daniel van der Wal on 06.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import "CameraConnector.h"

@implementation CameraConnector

- (id)init {
    if (self = [super init])
    {
        //_connected = false;
    }
    return self;
}


- (void)connectToCamera:(int)camId {
    // 0=default, -1=any camera, 1..99 your camera
    _camera = cv::VideoCapture(camId);
        cv::namedWindow("Camera");
        while (_camera.isOpened()) {
            cv::Mat frame;
            _camera >> frame;
            cv::imshow("Camera", frame);
            if(cv::waitKey(30) >= 0) break;
    }
    _camera.release();
    cv::destroyWindow("Camera");
    
    /*_camera = cvCaptureFromCAM(0);
    _connected = true;
    cvNamedWindow("Camera", CV_WINDOW_AUTOSIZE );
    
    if(_camera)
    {
        while(_connected)
        {
            int grabbed = cvGrabFrame(_camera);
            if(grabbed) {
                
            }
            
        }
    }*/
}

- (void)closeCameraConnection:(int)camId {
    //cvReleaseCapture(&_camera);
    //cvDestroyWindow("result");
}

@end
