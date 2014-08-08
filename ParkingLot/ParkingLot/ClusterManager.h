//
//  ClusterManager.h
//  ParkingLot
//
//  Created by Robert Scherbarth on 06.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "MessageProtocol.h"
#import "ImageCapturerProtocol.h"

@interface ClusterManager : NSObject<MessageProtocol, ImageCapturerProtocol> {
    
    @private
    NSMutableArray *_imageProcessorWorker;

    NSMutableArray *_imageCapturerWorkers;
    
    NSHost *_hostname;
    NSConnection *_theConnection;
    NSSocketPort *_sockPort;
    
}

-(void)startClusterManager;
-(void)stopClusterManager;

@end
