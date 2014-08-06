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
        _hostname = [NSHost currentHost];
        
        
        // Create a new socket port for your connection.
        _sockPort = [[NSSocketPort alloc] initWithTCPPort:1234];
        
        //Create a connection object
        _theConnection = [[NSConnection alloc] init];

    }
    return self;
}

- (void)startClusterManager{

    _theConnection = [NSConnection connectionWithReceivePort:_sockPort sendPort:_sockPort];
    
    //Set object to vend
    [_theConnection setRootObject:self];
    
    if ([_theConnection registerName:@"clusterManager" withNameServer: [NSSocketPortNameServer sharedInstance]] == NO) {
        NSLog(@"Impossible to vend this object.");
    } else {
        NSLog(@"Object vended.");
    }
    
}

/*
 *  Method to stop the ClusterManager
 */

-(void)stopClusterManager{

    //Funktioniert nicht. Wahrscheinlich bleibt der Port und ein Connection-Objekt irgendwie im
    //Arbeitsspeicher bestehen.
    
    _sockPort = nil;
    _theConnection = [NSConnection connectionWithReceivePort:_sockPort sendPort:_sockPort];
    
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

    NSLog(@"Removed client");
    
    return YES;
}


@end
