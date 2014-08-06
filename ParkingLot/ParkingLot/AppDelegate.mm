//
//  AppDelegate.m
//  ParkingLot
//
//  Created by Daniel van der Wal on 06.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import "AppDelegate.h"

@implementation AppDelegate

- (void)applicationDidFinishLaunching:(NSNotification *)aNotification
{
    // Insert code here to initialize your application
    _cameraConnector = [[CameraConnector alloc] init];
}

- (IBAction)startCamera:(id)sender {
    [_cameraConnector connectToCamera:0];
}

- (IBAction)stopCamera:(id)sender {
}

- (IBAction)startClusterManger:(id)sender {
}

- (IBAction)stopClusterManager:(id)sender {
}

- (IBAction)addClient:(id)sender {
}

- (IBAction)removeClient:(id)sender {
}
@end
