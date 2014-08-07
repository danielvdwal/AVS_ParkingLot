//
//  ImageCapturer.h
//  ParkingLot
//
//  Created by Daniel van der Wal on 07.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "ImageCapturedDelegate.h"
#import "CameraConnector.h"
#import "ClusterManagerImageCapturerConnector.h"

@interface ImageCapturer : NSObject<ImageCapturedDelegate> {
    @private
    CameraConnector *_cameraConnector;
    ClusterManagerConnector *_clusterManagerConnector;
}

- (void)startCamera;
- (void)stopCamera;
- (void)connectToClusterManager;
- (void)closeClusterMangerConnection;

@end
