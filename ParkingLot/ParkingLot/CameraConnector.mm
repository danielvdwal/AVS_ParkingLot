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

- (id)initWithDelegate:(id)delegate {
    if (self = [super init])
    {
        _connected = NO;
        _delegate = delegate;
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

- (void)connectToCamera:(int)camId {
    // 0=default, -1=any camera, 1..99 your camera
    _camera = cv::VideoCapture(camId);
    NSThread *workerThread = [[NSThread alloc] initWithTarget:self
                                                     selector:@selector(doWork:)
                                                       object:nil];
    _connected = YES;
    [workerThread start];
}

-(void)doWork:(id)arg {
    while(_connected) {
        @autoreleasepool {
            cv::Mat frame;
            _camera >> frame;
            NSImage *img = [NSImage imageWithCVMat:frame];
            [_delegate setImage:img];
            frame.release();
            if(cv::waitKey(30) >= 0) {
                _connected = false;
                break;
            }
        }
    }
    _camera.release();
}

- (void)closeCameraConnection:(int)camId {
    //cvReleaseCapture(&_camera);
    //cvDestroyWindow("result");
}

@end
