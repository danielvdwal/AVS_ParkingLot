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
}

- (IBAction)startCamera:(id)sender {
}

- (IBAction)stopCamera:(id)sender {
}

- (IBAction)startClusterManger:(id)sender {
    
    ClusterManager *clusterManager = [[ClusterManager alloc] init];
    [clusterManager startClusterManager];
    
}

- (IBAction)stopClusterManager:(id)sender {
    
    ClusterManager *clusterManager = [[ClusterManager alloc] init];
    [clusterManager stopClusterManager];
    
}

- (IBAction)addClient:(id)sender {
    
    ImageProcessorWorker *imageProcessorWorker = [[ImageProcessorWorker alloc] init];
    [imageProcessorWorker startImageProcessorWorker];
    
}

- (IBAction)removeClient:(id)sender {
    
    NSHost *hostname = [NSHost currentHost];
    NSLog(@"%@",[hostname name]);
    
}
@end
