//
//  ClusterManager.m
//  ParkingLot
//
//  Created by Robert Scherbarth on 06.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import "ClusterManager.h"

@implementation ClusterManager


-(id) init {
    if(self=[super init]){
        _imageProcessorWorker = [[NSMutableArray alloc] init];
        hostname = [NSHost currentHost];
        
    }
    return self;
}

- (void)startClusterManager{
    
    /*
     * Create a new socket port for your connection.
     */
    sockPort = [[NSSocketPort alloc] initWithTCPPort:1234];
    theConnection = [[NSConnection alloc] init];
    
    theConnection = [NSConnection connectionWithReceivePort:sockPort sendPort:sockPort];
    
    //Set object to vend
    [theConnection setRootObject:self];
    
    if ([theConnection registerName:@"clusterManager" withNameServer: [NSSocketPortNameServer sharedInstance]] == NO) {
        NSLog(@"Impossible to vend this object.");
    } else {
        NSLog(@"Object vended.");
    }
    
}

/*
 *  Method to stop the ClusterManager
 */

-(void)stopClusterManager{

    [theConnection registerName:nil];
    //[[theConnection sendPort] invalidate];
    //[[theConnection receivePort] invalidate];
    //[theConnection invalidate];
    theConnection = nil;
    
    NSLog(@"Server closed.");
    
}

- (void) doSomething {
    NSLog(@"do something : ");
    for(int i = 0; i < 5; i++) {
        NSLog(@".");
        [NSThread sleepForTimeInterval:1.0];
    }
    NSLog(@"end ...");
}

- (void)addClient
{
    _numberOfClients++;
    NSLog(@"Added client");
}

- (void)addImageProcessorWorker:(NSHost*)worker
{
    [_imageProcessorWorker addObject: worker];
    NSLog(@"Added ImageProcessorWorker with Hostname %@",[worker name]);
    
    NSLog(@"Worker active : %lu", [_imageProcessorWorker count]);
}

- (BOOL)removeImageProcessorWorker:(NSHost*)worker
{
    [_imageProcessorWorker removeObjectIdenticalTo: worker];
    NSLog(@"Removed ImageProcessorWorker with Hostname %@",[worker name]);
    
    NSLog(@"Worker active : %lu", [_imageProcessorWorker count]);
    
    return YES;
}

- (BOOL)removeClient
{
    _numberOfClients--;
    NSLog(@"Removed client");
    
    return YES;
}


@end
