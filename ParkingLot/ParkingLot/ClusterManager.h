//
//  ClusterManager.h
//  ParkingLot
//
//  Created by Robert Scherbarth on 06.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface ClusterManager : NSObject

{
    int      *_numberOfClients;
    NSMutableArray *_imageProcessorWorker;
    NSHost *hostname;
    NSConnection *theConnection;
    NSSocketPort *sockPort;
}

-(void)startClusterManager;
-(void)stopClusterManager;

@end
