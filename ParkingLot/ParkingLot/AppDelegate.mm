//
//  AppDelegate.m
//  ParkingLot
//
//  Created by Daniel van der Wal on 06.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import "AppDelegate.h"
#import "ClusterManager.h"
#import "ImageProcessorWorker.h"

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

- (IBAction)showClusterManagerWindow:(id)sender {
   
    if(!_clusterManagerWindow){
        
        _clusterManagerWindow = [[ClusterManagerWindow alloc] initWithWindowNibName:@"ClusterManagerWindow"];
        
    }
    [_clusterManagerWindow showWindow:self];
    
}
@end
