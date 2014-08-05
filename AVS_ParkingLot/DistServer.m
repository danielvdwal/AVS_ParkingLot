//
//  DistServer.m
//  AVS_ParkingLot
//
//  Created by Robert Scherbarth on 05.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import "DistServer.h"

@implementation DistServer

    NSConnection *theConnection;
    NSSocketPort *sockPort;

- (void)startServer{
    
    /*
     * Create a new socket port for your connection.
     */
    sockPort = [[NSSocketPort alloc] initWithTCPPort:1234];
    
    theConnection = [NSConnection new];
    theConnection = [NSConnection connectionWithReceivePort:sockPort sendPort:sockPort];
    
    //Set object to vend
    //[theConnection setRootObject: server];
    
    if ([theConnection registerName:@"server" withNameServer: [NSSocketPortNameServer sharedInstance]] == NO) {
        NSLog(@"Impossible to vend this object.");
    } else {
        NSLog(@"Object vended.");
    }
    
}

@end
