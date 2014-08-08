//
//  ClusterManagerConnector.m
//  ParkingLot
//
//  Created by Daniel van der Wal on 07.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import "ClusterManagerImageCapturerConnector.h"
#import "ImageCapturerProtocol.h"
#import "WrappedImage.h"

@implementation ClusterManagerConnector

- (void)connectToClusterManager {
    NSConnection *connection;
    NSSocketPort *socketPort;
    
    socketPort = [[NSSocketPort alloc]  initRemoteWithTCPPort: 1234
                                                         host: @"pip07.inf.fh-koeln.de"];
    
    connection = [NSConnection  connectionWithReceivePort: nil
                                                 sendPort: socketPort];
    
    
    _clusterManager = [[connection rootProxy] self];
    
    
    [_clusterManager setProtocolForProxy:@protocol(ImageCapturerProtocol)];
    
    _worker = [NSHost currentHost];
    
    [_clusterManager addImageCapturerWorker:_worker];
}

- (void)closeConnectionToClusterManager {
    [_clusterManager removeImageCapturerWorker:_worker];
}

- (void)forwardImage:(const cv::Mat&)image {
    if(_clusterManager) {
        WrappedImage *wrappedImage = [[WrappedImage alloc] initWithCvMat:image];
        [_clusterManager forwardImage:wrappedImage
                           fromWorker:_worker];
    }
}

@end
