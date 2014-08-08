//
//  ClusterManagerWindow.m
//  ParkingLot
//
//  Created by Robert Scherbarth on 06.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import "ClusterManagerWindow.h"


@interface ClusterManagerWindow ()

@end

@implementation ClusterManagerWindow

- (id)initWithWindow:(NSWindow *)window
{
    self = [super initWithWindow:window];
    if (self) {
        // Initialization code here.
    }
    return self;
}

- (void)windowDidLoad
{
    [super windowDidLoad];
    
    // Implement this method to handle any initialization after your window controller's window has been loaded from its nib file.
    
    NSLog(@"Window did load.");
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
