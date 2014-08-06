//
//  ImageProcessorWorker.m
//  ParkingLot
//
//  Created by Robert Scherbarth on 06.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import "ImageProcessorWorker.h"
#import "MessageProtocol.h"

@implementation ImageProcessorWorker

- (void)startImageProcessorWorker{
    
    NSConnection *theConnection;
    NSSocketPort *sockPort;

    id clusterManager;
    
    sockPort = [[NSSocketPort alloc]  initRemoteWithTCPPort: 1234
                                                       host: @"pip09.inf.fh-koeln.de"];
    
    theConnection = [NSConnection  connectionWithReceivePort: nil
                                                    sendPort: sockPort];
    
    
    clusterManager = [[theConnection rootProxy] self];
    
    
    [clusterManager setProtocolForProxy:@protocol(MessageProtocol)];
    
    NSHost *worker = [NSHost currentHost];
    
    [clusterManager addImageProcessorWorker:worker];
    [clusterManager doSomething];
    NSLog(@"Work finished.");
    [clusterManager removeImageProcessorWorker:worker];

}

@end
