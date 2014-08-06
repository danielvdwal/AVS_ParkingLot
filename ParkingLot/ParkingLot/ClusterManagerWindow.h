//
//  ClusterManagerWindow.h
//  ParkingLot
//
//  Created by Robert Scherbarth on 06.08.14.
//  Copyright (c) 2014 VirginCode. All rights reserved.
//

#import <Cocoa/Cocoa.h>
#import "ClusterManager.h"
#import "ImageProcessorWorker.h"

@interface ClusterManagerWindow : NSWindowController



- (IBAction)startClusterManger:(id)sender;
- (IBAction)stopClusterManager:(id)sender;

- (IBAction)addClient:(id)sender;
- (IBAction)removeClient:(id)sender;

@end
