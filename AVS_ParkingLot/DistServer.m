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
    [theConnection setRootObject:self];
    
    if ([theConnection registerName:@"server" withNameServer: [NSSocketPortNameServer sharedInstance]] == NO) {
        NSLog(@"Impossible to vend this object.");
    } else {
        NSLog(@"Object vended.");
    }
    
}

-(void)stopServer{
    
    [[theConnection sendPort] invalidate];
    [[theConnection receivePort] invalidate];
    [theConnection invalidate];
    
    //[theConnection registerName:nil];
    NSLog(@"Server closed!");

}

- (void) message {
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

- (BOOL)removeClient
{
    _numberOfClients--;
    NSLog(@"Removed client");
    
    return YES;
}

@end
