//
//  CameraConnector.mm
//  ParkingLot
//
//  Created by Daniel van der Wal on 06.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import "CameraConnector.h"
#import "NSImage_OpenCV.h"

@implementation CameraConnector

- (id)init {
    if (self = [super init])
    {
        _connected = NO;
    }
    return self;
}


- (void)connectToCameraOpenCV:(int)camId {
    // 0=default, -1=any camera, 1..99 your camera
    _camera = cv::VideoCapture(camId);
    cv::namedWindow("Camera");
    while (_camera.isOpened()) {
        cv::Mat frame;
        _camera >> frame;
        cv::imshow("Camera", frame);
        frame.release();
        if(cv::waitKey(30) >= 0) {
            break;
        }
    }
    _camera.release();
    cv::destroyWindow("Camera");
}

- (void)connectToCamera:(int)camId
         AndSendImageTo:(NSImageView *)view {
    
    // 0=default, -1=any camera, 1..99 your camera
    _camera = cv::VideoCapture(camId);
    NSThread *workerThread = [[NSThread alloc] initWithTarget:self
                                                     selector:@selector(doWork:)
                                                       object:view];
    _connected = YES;
    [workerThread start];
}

-(void)doWork:(id)arg {
    NSImage *img;
        while(_connected) {
            cv::Mat frame;
            _camera >> frame;
            img = [NSImage imageWithCVMat:frame];
            //[(NSImageView*)arg setImage:img];
            frame.release();
            if(cv::waitKey(30) >= 0) {
                _connected = false;
                break;
            }
        }
        _camera.release();
}

- (void)closeCameraConnection:(int)camId {
    //cvReleaseCapture(&_camera);
    //cvDestroyWindow("result");
}

@end
