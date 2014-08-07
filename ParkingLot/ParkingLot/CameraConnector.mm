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

- (id)initWithDelegate:(id<ImageCapturedDelegate>)delegate {
    if (self = [super init])
    {
        _delegate = delegate;
        _connected = NO;
    }
    return self;
}


- (void)connectToDefaultCamera {
    // 0=default, -1=any camera, 1..99 your camera
    _camera = cv::VideoCapture(0);
    _connected = YES;
}

- (void)closeDefaultCameraConnection {
    _connected = NO;
    [_captureThread cancel];
}

- (void)startCapturing {
    _captureThread = [[NSThread alloc] initWithTarget:self
                                             selector:@selector(capture:)
                                               object:nil];
    [_captureThread start];
}


-(void)capture:(id)arg {
    while (_camera.isOpened()) {
        cv::Mat frame;
        _camera >> frame;
        
        [_delegate imageCaptured:frame];
        
        frame.release();
        
        cv::waitKey(33);
        
        if(!_connected) {
            _camera.release();
        }
    }
}

@end
