//
//  CameraConnector.h
//  ParkingLot
//
//  Created by Daniel van der Wal on 06.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import "ImageCapturedDelegate.h"

@interface CameraConnector : NSObject {
    @private
    cv::VideoCapture _camera;
    BOOL _connected;
    id<ImageCapturedDelegate> _delegate;
    NSThread *_captureThread;
}

- (id)initWithDelegate:(id<ImageCapturedDelegate>)delegate;
- (void)connectToDefaultCamera;
- (void)closeDefaultCameraConnection;
- (void)startCapturing;

@end
